package br.com.calcard.loan.resource.v1.simulacao;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calcard.loan.dto.simulacao.SimularPropostasRequest;
import br.com.calcard.loan.dto.simulacao.SimularPropostasResponse;
import br.com.calcard.loan.service.simulacao.SimulacaoService;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/simulacao")
public class SimulacaoResource {

    private final SimulacaoService simulacaoService;

    @PostMapping
    public SimularPropostasResponse simularPropostas(@RequestBody @Validated final SimularPropostasRequest request) {
        return simulacaoService.simularPropostas(request);
    }
}
