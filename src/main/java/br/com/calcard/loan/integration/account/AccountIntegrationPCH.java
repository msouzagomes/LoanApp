package br.com.calcard.loan.integration.account;

import br.com.calcard.loan.integration.config.FeignIntegrationConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AccountIntegrationPCH", qualifier = "PCH",
        url = "${api.egress.host}${api.egress.platform.pch}",
        configuration = {FeignIntegrationConfig.class})
public interface AccountIntegrationPCH extends AccountIntegration {
}