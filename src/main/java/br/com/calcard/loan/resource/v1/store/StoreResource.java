package br.com.calcard.loan.resource.v1.store;

import static br.com.calcard.loan.dto.RequestHeaders.IP;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calcard.loan.dto.store.CurrentStoreResponse;
import br.com.calcard.loan.service.store.StoreService;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/store")
public class StoreResource {

    private final StoreService storeService;

    @GetMapping
    public CurrentStoreResponse getCurrentStore(HttpServletRequest request) {

        return storeService.getCurrentStoreByIp(request.getHeader(IP));
    }
}
