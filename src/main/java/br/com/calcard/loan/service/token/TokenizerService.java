package br.com.calcard.loan.service.token;

import static br.com.calcard.loan.dto.ErrorCode.EMAIL_NAO_VALIDADO;
import static br.com.calcard.loan.dto.ErrorCode.ERRO_GERACAO_TOKEN;
import static br.com.calcard.loan.dto.ErrorCode.TOKEN_INVALIDO;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.calcard.loan.annotation.Auditoria;
import br.com.calcard.loan.dto.token.ValidarTokenRequest;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.loan.LoanClient;
import br.com.calcard.loan.integration.loan.dto.RegistrarValidacaoTokenRequest;
import br.com.calcard.loan.integration.loan.dto.SalvarTokenRequest;
import br.com.calcard.loan.integration.loan.dto.TipoEnvioToken;
import br.com.calcard.tokenizer.exception.TokenizerConfigException;
import br.com.calcard.tokenizer.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenizerService {

    @Value("${tokenizer.ttl}")
    private Long tempoExpiracao;

    private final TokenService tokenService;
    private final MessageHelper messageHelper;
    private final LoanClient loanClient;

    public String gerarToken(final String valorTipo, final String cpf, final TipoEnvioToken tipoEnvio) {

        final String chaveRedis = cpf + valorTipo;

        log.info("Gerando token para {}.", sha256Hex(cpf));

        tokenService.expire(chaveRedis);

        try {
            final String token = tokenService.generateTokenizerForKey(chaveRedis);

            loanClient.salvarToken(SalvarTokenRequest.builder()
                .token(token)
                .cpf(cpf)
                .tipoEnvio(tipoEnvio)
                .valorTipo(valorTipo)
                .validade(LocalDateTime.now().plusSeconds(tempoExpiracao))
                .build());

            return token;
        } catch (final TokenizerConfigException e) {
            log.error("Erro no proceso de geração de token.", e);
            throw new BusinessErrorException(ERRO_GERACAO_TOKEN, messageHelper.get(ERRO_GERACAO_TOKEN));
        }
    }

    @Auditoria
    public void validarToken(final ValidarTokenRequest request) {

        log.info("Validando token para {}.", sha256Hex(request.getCpf()));

        final String chaveRedis = request.getCpf() + request.getTelefone();

        Optional.of(tokenService.validate(chaveRedis, request.getToken()))
            .filter(BooleanUtils::isTrue)
            .orElseThrow(() -> new BusinessErrorException(TOKEN_INVALIDO, messageHelper.get(TOKEN_INVALIDO)));

        loanClient.registrarValidacaoToken(
            RegistrarValidacaoTokenRequest.builder()
                .tipoEnvio(TipoEnvioToken.CELULAR)
                .cpf(request.getCpf())
                .build());

        tokenService.expire(chaveRedis);
    }

    public void confirmarEmail(final String token) {

        final RegistrarValidacaoTokenRequest tokenRequest = RegistrarValidacaoTokenRequest.builder()
            .token(token)
            .tipoEnvio(TipoEnvioToken.EMAIL)
            .build();

        loanClient.registrarValidacaoToken(tokenRequest);
    }

    public void verificarValidacaoToken(final String cpf, final TipoEnvioToken tipoEnvio) {
        try {
            loanClient.verificarValidacaoToken(cpf, tipoEnvio);
        } catch (final Exception exception) {
            throw new BusinessErrorException(EMAIL_NAO_VALIDADO, messageHelper.get(EMAIL_NAO_VALIDADO));
        }
    }
}
