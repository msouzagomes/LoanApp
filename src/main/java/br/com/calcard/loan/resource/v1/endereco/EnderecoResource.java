package br.com.calcard.loan.resource.v1.endereco;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calcard.loan.dto.endereco.ConsultarEnderecoResponse;
import br.com.calcard.loan.service.endereco.EnderecoService;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/endereco")
public class EnderecoResource {

    private final EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ConsultarEnderecoResponse consultarEndereco(
        @PathVariable("cep") final String cep) {
        return enderecoService.consultarEndereco(cep);
    }

}
