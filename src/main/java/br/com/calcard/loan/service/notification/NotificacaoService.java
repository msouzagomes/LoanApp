package br.com.calcard.loan.service.notification;

import static br.com.calcard.loan.dto.Ambiente.DEV;
import static br.com.calcard.loan.dto.Ambiente.HML;
import static br.com.calcard.loan.dto.Ambiente.PRD;
import static br.com.calcard.loan.dto.ErrorCode.MENSAGEM_TOKENIZACAO_NAO_ENCONTRADA;
import static java.util.Collections.singletonList;
import static java.util.Optional.ofNullable;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

import br.com.calcard.loan.dto.notificacao.EnviarTokenEmailRequest;
import br.com.calcard.loan.integration.notificacao.dto.SendEmailRequestDTO;
import org.springframework.stereotype.Service;

import br.com.calcard.loan.config.property.NotificacaoConfig;
import br.com.calcard.loan.dto.notificacao.EnviarTokenSmsRequest;
import br.com.calcard.loan.dto.token.Placeholder;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.loan.LoanClient;
import br.com.calcard.loan.integration.loan.dto.ConsultarMensagemTokenRequest;
import br.com.calcard.loan.integration.loan.dto.ConsultarMensagemTokenResponse;
import br.com.calcard.loan.integration.loan.dto.TipoEnvioToken;
import br.com.calcard.loan.integration.notificacao.NotificacaoClient;
import br.com.calcard.loan.integration.notificacao.dto.SendSMSRequestDTO;
import br.com.calcard.loan.service.token.TokenizerService;
import br.com.calcard.loan.validator.CpfValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoClient notificacaoClient;
    private final CpfValidator cpfValidator;
    private final TokenizerService tokenizerService;
    private final NotificacaoConfig config;
    private final LoanClient loanClient;
    private final MessageHelper messageHelper;

    public void enviarSms(final EnviarTokenSmsRequest request) {

        cpfValidator.validar(request.getCpf());

        log.info("Enviando token por SMS para {}", sha256Hex(request.getCpf()));

        final ConsultarMensagemTokenRequest consultarMensagem = ConsultarMensagemTokenRequest.builder()
            .cpf(request.getCpf())
            .tipoEnvio(TipoEnvioToken.CELULAR)
            .valorTipo(request.getTelefone())
            .build();

        final ConsultarMensagemTokenResponse response = ofNullable(
            loanClient.consultarMensagemToken(consultarMensagem))
            .orElseThrow(() -> new BusinessErrorException(MENSAGEM_TOKENIZACAO_NAO_ENCONTRADA,
                messageHelper.get(MENSAGEM_TOKENIZACAO_NAO_ENCONTRADA)));

        final String token = ofNullable(response.getToken())
            .orElseGet(() -> tokenizerService.gerarToken(request.getTelefone(), request.getCpf(), TipoEnvioToken.CELULAR));

        final SendSMSRequestDTO sendSMSRequestDTO = SendSMSRequestDTO.builder()
            .mobileNumbers(singletonList(config.getCodigo() + request.getTelefone()))
            .messageText(ofNullable(response.getMensagem())
                .map(mensagem -> mensagem.replace(Placeholder.TOKEN.getValor(), token))
                .orElse(token))
            .build();

        if (config.getAmbientes().contains(DEV) || config.getAmbientes().contains(HML)) {
            log.info("Enviando token por SMS para {} no ambiente de DEV ou HML", sha256Hex(request.getCpf()));

            if (config.getNumerosPermitidos().contains(request.getTelefone())) {

                log.info("Enviando token por SMS para número permitido de {}", sha256Hex(request.getCpf()));
                notificacaoClient.sendSMS(sendSMSRequestDTO);
            }
        } else if (config.getAmbientes().contains(PRD)) {
            log.info("Enviando token por SMS para {} no ambiente de PRD", sha256Hex(request.getCpf()));
            notificacaoClient.sendSMS(sendSMSRequestDTO);
        }
    }

    public void enviarEmail(EnviarTokenEmailRequest request) {
        cpfValidator.validar(request.getCpf());

        log.info("Enviando email para {}", sha256Hex(request.getCpf()));

        final ConsultarMensagemTokenRequest consultarMensagem = ConsultarMensagemTokenRequest.builder()
                .cpf(request.getCpf())
                .tipoEnvio(TipoEnvioToken.EMAIL)
                .valorTipo(request.getEmail())
                .build();

        final ConsultarMensagemTokenResponse response = ofNullable(
                loanClient.consultarMensagemToken(consultarMensagem))
                .orElseThrow(() -> new BusinessErrorException(MENSAGEM_TOKENIZACAO_NAO_ENCONTRADA,
                        messageHelper.get(MENSAGEM_TOKENIZACAO_NAO_ENCONTRADA)));

        final var sendEmailRequestDto = SendEmailRequestDTO.builder()
                .fromEmailAddress(config.getEmailRemetente())
                .toEmailAddress(request.getEmail())
                .subject(config.getSubtitulo())
                .messageText(response.getMensagem())
                .build();

        if (config.getAmbientes().contains(DEV) || config.getAmbientes().contains(HML)) {
            log.info("Enviando link por email para {} no ambiente de DEV ou HML", sha256Hex(request.getCpf()));

            if (config.getEmailsPermitidos().contains(request.getEmail())) {

                log.info("Enviando link para email permitido de {}", sha256Hex(request.getCpf()));
                notificacaoClient.sendEmail(sendEmailRequestDto);
            }
        } else if (config.getAmbientes().contains(PRD)) {
            log.info("Enviando link por email para {} no ambiente de PRD", sha256Hex(request.getCpf()));
            notificacaoClient.sendEmail(sendEmailRequestDto);
        }
    }
}
