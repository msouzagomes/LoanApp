package br.com.calcard.loan.resource.v1.liberacao;

import br.com.calcard.loan.dto.liberacao.LiberacaoTedRequest;
import br.com.calcard.loan.service.liberacao.LiberacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/liberacao")
public class LiberacaoResource {

  private LiberacaoService liberacaoService;

    @PostMapping("/ted")
    @ResponseStatus(OK)
    public void salvarLiberacaoTed(@RequestBody @Valid LiberacaoTedRequest request) {
        this.liberacaoService.salvarLiberacaoTed(request);
    }
}