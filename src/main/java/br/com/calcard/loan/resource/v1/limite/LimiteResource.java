package br.com.calcard.loan.resource.v1.limite;

import static br.com.calcard.loan.dto.RequestHeaders.IP;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.calcard.loan.dto.limite.ConsultarLimiteResponse;
import br.com.calcard.loan.service.limite.LimiteService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/limite")
public class LimiteResource {

    private final LimiteService limiteService;

    @GetMapping("/consultaLimite")
    @Timed
    public ConsultarLimiteResponse consultaLimite(@RequestParam(value = "cpf") final String cpf,
        final HttpServletRequest servletRequest) {
        return limiteService.consultaLimite(cpf, servletRequest.getHeader(IP));
    }

}
