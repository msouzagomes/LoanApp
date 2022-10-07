package br.com.calcard.loan.integration.loan;

import br.com.calcard.loan.dto.liberacao.LiberacaoTedRequest;
import br.com.calcard.loan.integration.loan.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.calcard.loan.dto.banco.ConsultarListaBancosResponse;
import br.com.calcard.loan.dto.limite.ConsultarLimiteResponse;
import br.com.calcard.loan.dto.simulacao.SimularPropostasResponse;
import br.com.calcard.loan.integration.config.FeignIntegrationConfig;

@FeignClient(name = "LoanClient", url = "${api.path.loan.host}${api.path.loan.basePath}", configuration = {
    FeignIntegrationConfig.class})
public interface LoanClient {

    @PostMapping(value = "${api.path.loan.limite.basePath}${api.path.loan.limite.consultaLimite}",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    ConsultarLimiteResponse consultaLimite(@RequestBody final ConsultarLimiteClientRequest consultarLimiteRequest);

    @PostMapping(value = "${api.path.loan.simulacao.basePath}", consumes = MediaType.APPLICATION_JSON_VALUE)
    SimularPropostasResponse simularPropostas(@RequestBody SimulacaoClientRequest request);

    @PostMapping(value = "${api.path.loan.cliente.basePath}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ClienteResponse criaCliente(CriaClienteRequest request);

    @PutMapping(value = "${api.path.loan.cliente.basePath}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ClienteResponse atualizaCliente(AtualizaClienteRequest request);

    @GetMapping(value = "${api.path.loan.cliente.basePath}${api.path.loan.cliente.cliente.porId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ClienteResponse consultaClientePorCodigo(@PathVariable("codigoCliente") String codigoCliente);

    @GetMapping(value = "${api.path.loan.cliente.basePath}${api.path.loan.cliente.cliente.porCpf}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ClienteResponse consultaClientePorCpf(@PathVariable("cpf") String cpf);
    @GetMapping(value = "${api.path.loan.endereco.basePath}${api.path.loan.endereco.consultarEndereco}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ConsultarEnderecoClientResponse consultarEndereco(@PathVariable("cep") String cep);

    @GetMapping(value = "${api.path.loan.agencia.basePath}${api.path.loan.agencia.consultarAgencia}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void consultarAgencia(@PathVariable("codigoBanco") String codigoBanco, @PathVariable("codigoAgencia") String codigoAgencia);
    
    @GetMapping(value = "${api.path.loan.banco.basePath}${api.path.loan.banco.lista}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ConsultarListaBancosResponse consultarListaBanco();
    
    @PostMapping(value = "${api.path.loan.token.basePath}${api.path.loan.token.consultarMensagem}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ConsultarMensagemTokenResponse consultarMensagemToken(@RequestBody ConsultarMensagemTokenRequest request);

    @PostMapping(value = "${api.path.loan.token.basePath}${api.path.loan.token.registrarValidacao}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void registrarValidacaoToken(@RequestBody RegistrarValidacaoTokenRequest request);

    @PostMapping(value = "${api.path.loan.token.basePath}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void salvarToken(@RequestBody SalvarTokenRequest request);

    @GetMapping(value = "${api.path.loan.token.basePath}${api.path.loan.token.verificarValidacao}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void verificarValidacaoToken(@RequestParam("cpf") final String cpf, @RequestParam("tipoEnvio") final TipoEnvioToken tipoEnvio);

    @PostMapping(value = "${api.path.loan.token.basePath}${api.path.loan.liberacao.ted}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void salvarLiberacaoTed(@RequestBody LiberacaoTedRequest request);

}
