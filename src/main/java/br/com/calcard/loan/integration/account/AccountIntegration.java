package br.com.calcard.loan.integration.account;

import br.com.calcard.loan.integration.account.dto.AccountDetailPierlabsDTO;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface AccountIntegration {

    @GetMapping(value="${api.egress.account.v2.find-detail}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Optional<AccountDetailPierlabsDTO> findAccountDetailById(@PathVariable("id") Long accountId);
}