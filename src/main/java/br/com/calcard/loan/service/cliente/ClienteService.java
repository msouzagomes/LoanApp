package br.com.calcard.loan.service.cliente;


import br.com.calcard.loan.integration.loan.LoanClient;
import br.com.calcard.loan.integration.loan.dto.AtualizaClienteRequest;
import br.com.calcard.loan.integration.loan.dto.ClienteResponse;
import br.com.calcard.loan.integration.loan.dto.CriaClienteRequest;
import br.com.calcard.loan.service.person.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {

    private final LoanClient loanCliente;
    private final PersonService personService;

    public ClienteResponse criaCliente(CriaClienteRequest request) {

        // TODO verificar Black list

        // TODO Atualizar Autbank

        // TODO Atualização Cadastral na Conductor (Calcard)

        return loanCliente.criaCliente(request);
    }

    public ClienteResponse atualizaCliente(AtualizaClienteRequest request) {


        // TODO verificar Black list

        // TODO Atualizar Autbank

        // TODO Atualização Cadastral na Conductor (Calcard)

        // TODO Atualização Cadastral no Função

        return loanCliente.atualizaCliente(request);
    }

    public ClienteResponse consultaClientePorCodigoCliente(String codigoCliente) {
        return loanCliente.consultaClientePorCodigo(codigoCliente);
    }

    public ClienteResponse consultaClientePorCpf(String cpf) {
        return loanCliente.consultaClientePorCpf(cpf);
    }

}

