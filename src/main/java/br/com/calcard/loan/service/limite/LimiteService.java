package br.com.calcard.loan.service.limite;

import static java.util.Optional.ofNullable;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import br.com.calcard.loan.annotation.Auditoria;
import br.com.calcard.loan.builder.ConsultaLimiteRequestBuilder;
import br.com.calcard.loan.dto.limite.ConsultarLimiteResponse;
import br.com.calcard.loan.dto.person.PersonResponse;
import br.com.calcard.loan.integration.account.AccountClient;
import br.com.calcard.loan.integration.account.dto.AccountDTO;

import br.com.calcard.loan.integration.account.dto.AccountDTO.Product;
import br.com.calcard.loan.integration.loan.LoanClient;
import br.com.calcard.loan.integration.store.StoreClient;
import br.com.calcard.loan.integration.store.dto.CurrentStoreDTO;
import br.com.calcard.loan.service.person.PersonService;
import br.com.calcard.loan.validator.CpfValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LimiteService {

    private final LoanClient loanClient;
    private final StoreClient storeClient;
    private final CpfValidator cpfValidator;
    private final PersonService personService;
    private final AccountClient accountClient;

    @Auditoria
    public ConsultarLimiteResponse consultaLimite(final String cpf, final String ip) {

        cpfValidator.validar(cpf);

        log.info("Consultando limite para o cpf {}", sha256Hex(cpf));

        final PersonResponse person = personService.getPersonByCpf(cpf);
        final CurrentStoreDTO currentStoreDTO = storeClient.getStoreByIp(ip);

        final Page<AccountDTO> accountDTOPage = accountClient.findByFilter(cpf);

        final boolean anuidade = ofNullable(accountDTOPage)
            .map(Slice::getContent)
            .flatMap(accounts -> accounts.stream()
                .filter(account -> ofNullable(account.getProduct())
                    .map(Product::getIdProduct).orElse(0L) == 157 && ofNullable(account.getProduct())
                    .map(Product::getStatus).orElse(null))
                .findAny())
            .isPresent();

        return loanClient
            .consultaLimite(ConsultaLimiteRequestBuilder.buildConsultaLimiteRequest(person, currentStoreDTO, anuidade));
    }

}
