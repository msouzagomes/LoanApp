package br.com.calcard.loan.resource.v1.promoter;

import static br.com.calcard.loan.dto.RequestHeaders.IP;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calcard.loan.dto.store.PromotersResponse;
import br.com.calcard.loan.service.promoter.PromoterService;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/promoter")
public class PromoterResource {

    private final PromoterService promotersService;

    @GetMapping("/current-store")
    public PromotersResponse getPromotersByCurrentStore(HttpServletRequest request) {
        return promotersService.getPromotersByIp(request.getHeader(IP));
    }
}
