package br.com.calcard.loan.service.liberacao;

import br.com.calcard.loan.dto.liberacao.LiberacaoTedRequest;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.loan.LoanClient;
import br.com.calcard.loan.validator.CpfValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.calcard.loan.dto.ErrorCode.LIBERACAO_INVALIDO;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@Service
@Slf4j
@RequiredArgsConstructor
public class LiberacaoService {

    private final LoanClient loanClient;
    private final MessageHelper messageHelper;
    private final CpfValidator cpfValidator;

    public void salvarLiberacaoTed(LiberacaoTedRequest request) {

        cpfValidator.validar(request.getCpf());

        log.info("Gravando informações do TED {}", sha256Hex(request.getCpf()));

        try {
            loanClient.salvarLiberacaoTed(request);
        } catch (Exception exception) {
            throw new BusinessErrorException(LIBERACAO_INVALIDO, messageHelper.get(LIBERACAO_INVALIDO));
        }

    }
}
