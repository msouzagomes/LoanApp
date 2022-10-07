package br.com.calcard.loan.resource.v1.token;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.calcard.loan.dto.token.ValidarTokenRequest;
import br.com.calcard.loan.integration.loan.dto.TipoEnvioToken;
import br.com.calcard.loan.service.token.TokenizerService;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/token")
public class TokenResource {

    private final TokenizerService tokenizerService;

    @PostMapping("/validacao")
    public void validarToken(@RequestBody final ValidarTokenRequest request) {
        tokenizerService.validarToken(request);
    }

    @GetMapping("/valida-email")
    public void validarEmail(@RequestParam("token") final String token) {
        tokenizerService.confirmarEmail(token);
    }

    @GetMapping("/validacao")
    public void verificarValidacaoToken(@RequestParam("cpf") final String cpf,
        @RequestParam("tipoEnvio") final TipoEnvioToken tipoEnvio) {
        tokenizerService.verificarValidacaoToken(cpf, tipoEnvio);
    }
}
