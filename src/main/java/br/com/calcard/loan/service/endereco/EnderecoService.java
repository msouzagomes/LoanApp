package br.com.calcard.loan.service.endereco;

import static br.com.calcard.loan.dto.ErrorCode.CEP_NAO_ENCONTRADO;
import static java.util.Optional.ofNullable;

import org.springframework.stereotype.Service;

import br.com.calcard.loan.builder.ConsultarEnderecoResponseBuilder;
import br.com.calcard.loan.dto.endereco.ConsultarEnderecoResponse;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.loan.LoanClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EnderecoService {

    private final LoanClient loanClient;
    private final MessageHelper messageHelper;

    public ConsultarEnderecoResponse consultarEndereco(final String cep) {

        log.info("Consultando informações do endereço para o CEP {}", cep);

        return ofNullable(loanClient.consultarEndereco(cep))
            .map(ConsultarEnderecoResponseBuilder::buildConsultarEnderecoResponse)
            .orElseThrow(() -> new BusinessErrorException(CEP_NAO_ENCONTRADO, messageHelper.get(CEP_NAO_ENCONTRADO)));
    }

}