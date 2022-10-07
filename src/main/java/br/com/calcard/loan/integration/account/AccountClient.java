package br.com.calcard.loan.integration.account;

import br.com.calcard.loan.integration.account.dto.AccountDTO;
import br.com.calcard.loan.integration.config.FeignConfigBasicAuth;
import br.com.calcard.loan.integration.config.FeignIntegrationConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "account", url = "${api.path.account.host}${api.path.account.basePath}",configuration = {
        FeignIntegrationConfig.class, FeignConfigBasicAuth.class})
public interface AccountClient {

    @GetMapping(value = "${api.path.account.account.details}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Page<AccountDTO> findByFilter(@RequestParam(value = "cpf", required = false) String cpf);
}
