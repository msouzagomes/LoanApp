package br.com.calcard.loan.integration.store;

import br.com.calcard.loan.integration.config.FeignConfigBasicAuth;
import br.com.calcard.loan.integration.config.FeignIntegrationConfig;
import br.com.calcard.loan.integration.store.dto.CurrentStoreDTO;
import br.com.calcard.loan.integration.store.dto.PromoterDTO;
import br.com.calcard.loan.integration.store.dto.StoreDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "StoreClient", url = "${api.path.store.host}${api.path.store.basePath}", configuration = {
    FeignIntegrationConfig.class, FeignConfigBasicAuth.class})
public interface StoreClient {

    @GetMapping(value = "${api.path.store.getStore}", consumes = MediaType.APPLICATION_JSON_VALUE)
    CurrentStoreDTO getStoreByIp(@RequestParam("ip") String ip);

    @GetMapping(value = "${api.path.store.getPromoters}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<PromoterDTO> getPromotersByIdStore(@PathVariable("id") String storeId);

    @GetMapping(value = "${api.path.store.getStore}", consumes = MediaType.APPLICATION_JSON_VALUE)
    StoreDTO getStoreStorage(@RequestParam("ip") String ip);

}
