package br.com.calcard.loan.service.agencia;

import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.loan.LoanClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.calcard.loan.dto.ErrorCode.AGENCIA_NAO_ENCONTRADA;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgenciaService {

    private final LoanClient loanClient;
    private final MessageHelper messageHelper;

    public void consultar(final String codigoBanco, final String codigoAgencia) {
        log.info("Consultando informações para o código do banco {} e código da agência {}", codigoBanco, codigoAgencia);

        try {
            loanClient.consultarAgencia(codigoBanco, codigoAgencia);
        } catch (Exception exception) {
            throw new BusinessErrorException(AGENCIA_NAO_ENCONTRADA, messageHelper.get(AGENCIA_NAO_ENCONTRADA));
        }
    }
}
