package br.com.calcard.loan.resource.v1.agencia;

import br.com.calcard.loan.service.agencia.AgenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/agencia")
public class AgenciaResource {

    private final AgenciaService agenciaService;

    @GetMapping("/{codigoBanco}/{codigoAgencia}")
    public void consultarAgencia(@PathVariable("codigoBanco") final String codigoBanco, @PathVariable("codigoAgencia") final String codigoAgencia) {
        agenciaService.consultar(codigoBanco, codigoAgencia);
    }
}
