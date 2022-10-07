package br.com.calcard.loan.resource.v1.cliente;


import br.com.calcard.loan.integration.loan.dto.AtualizaClienteRequest;
import br.com.calcard.loan.integration.loan.dto.ClienteResponse;
import br.com.calcard.loan.integration.loan.dto.CriaClienteRequest;
import br.com.calcard.loan.service.cliente.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;


@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cliente")
public class ClienteResource {

    private final ClienteService clienteService;

    @PostMapping()
    public ClienteResponse criaCliente(@RequestBody CriaClienteRequest request) {
        log.info("Cadastrando cliente ",sha256Hex(request.getCpf()));
        return clienteService.criaCliente(request);
    }

    @PutMapping()
    public ClienteResponse atualizaCliente(@RequestBody AtualizaClienteRequest request) {
        log.info("Atualizando cliente ",request.getCodigoCliente());
        return clienteService.atualizaCliente(request);
    }

    @GetMapping("/codigo/{codigoCliente}")
    public ClienteResponse consultaClientePorCodigoCliente(@PathVariable("codigoCliente") String codigoCliente) {
        log.info("Buscando cliente CodigoCliente: ",codigoCliente);
        return clienteService.consultaClientePorCodigoCliente(codigoCliente);
    }

    @GetMapping("/cpf/{cpf}")
    public ClienteResponse consultaClientePorCpf(@PathVariable("cpf") String cpf) {

        return clienteService.consultaClientePorCpf(cpf);
    }
}

