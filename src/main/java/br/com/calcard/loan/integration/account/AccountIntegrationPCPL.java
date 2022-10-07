
package br.com.calcard.loan.integration.account;

import br.com.calcard.loan.integration.config.FeignIntegrationConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AccountIntegrationPCPL", qualifier = "PCPL",
        url = "${api.egress.host}${api.egress.platform.pcpl}",
        configuration = {FeignIntegrationConfig.class})
public interface AccountIntegrationPCPL extends AccountIntegration {
}