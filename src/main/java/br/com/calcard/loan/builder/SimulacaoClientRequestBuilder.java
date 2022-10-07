package br.com.calcard.loan.builder;

import java.time.LocalDate;

import br.com.calcard.loan.dto.simulacao.SimularPropostasRequest;
import br.com.calcard.loan.integration.loan.dto.SimulacaoClientRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimulacaoClientRequestBuilder {

    public static SimulacaoClientRequest builSimulacaoClientRequest(final SimularPropostasRequest request,
        final LocalDate dataNascimento) {

        return SimulacaoClientRequest.builder()
            .cpf(request.getCpf())
            .valorFinanciado(request.getValorFinanciado())
            .dataPrimeiroVencimento(request.getDataPrimeiroVencimento())
            .dataNascimento(dataNascimento).build();
    }
}
