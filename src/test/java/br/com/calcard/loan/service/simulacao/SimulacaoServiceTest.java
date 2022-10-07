package br.com.calcard.loan.service.simulacao;

import static br.com.calcard.loan.dto.ErrorCode.ERRO_AO_SIMULAR_PROPOSTAS;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.math.RoundingMode;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.calcard.loan.dto.person.Person;
import br.com.calcard.loan.dto.simulacao.SimularPropostasRequest;
import br.com.calcard.loan.dto.simulacao.SimularPropostasResponse;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.fixture.Fixture;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.loan.LoanClient;
import br.com.calcard.loan.integration.loan.dto.SimulacaoClientRequest;
import br.com.calcard.loan.integration.person.PersonClient;
import br.com.calcard.loan.validator.CpfValidator;

@RunWith(MockitoJUnitRunner.class)
public class SimulacaoServiceTest {

    @Captor
    private ArgumentCaptor<SimulacaoClientRequest> simulacaoClientRequestArgumentCaptor;

    @InjectMocks
    private SimulacaoService simulacaoService;

    @Mock
    private PersonClient personClient;

    @Mock
    private CpfValidator cpfValidator;

    @Mock
    private LoanClient loanClient;

    @Mock
    private MessageHelper messageHelper;

    @Test
    public void simular_propostas_ok() {

        final SimularPropostasRequest request = Fixture.make(SimularPropostasRequest.builder().build());
        final Person person = Fixture.make(Person.builder().build());
        final SimularPropostasResponse response = Fixture.make(SimularPropostasResponse.builder().build());

        when(personClient.getPersonByCpf(request.getCpf())).thenReturn(java.util.Optional.of(person));
        when(loanClient.simularPropostas(any(SimulacaoClientRequest.class))).thenReturn(response);

        final SimularPropostasResponse actual = simulacaoService.simularPropostas(request);

        verify(cpfValidator).validar(request.getCpf());
        verify(personClient).getPersonByCpf(request.getCpf());
        verify(loanClient).simularPropostas(simulacaoClientRequestArgumentCaptor.capture());
        verifyNoInteractions(messageHelper);

        final SimulacaoClientRequest actualCaptor = simulacaoClientRequestArgumentCaptor.getValue();

        assertEquals(request.getCpf(), actualCaptor.getCpf());
        assertEquals(request.getValorFinanciado(), actualCaptor.getValorFinanciado());
        assertEquals(request.getDataPrimeiroVencimento(), actualCaptor.getDataPrimeiroVencimento());
        assertEquals(person.getBirthDate(), actualCaptor.getDataNascimento());

        final SimularPropostasResponse expected = of(response)
            .map(SimularPropostasResponse::getSimulacoes)
            .map(simulacoes -> simulacoes.stream()
                .map(simulacao -> simulacao.withTaxaCetAnual(ofNullable(simulacao.getTaxaCetAnual())
                    .map(value -> value.setScale(2, RoundingMode.HALF_EVEN))
                    .orElse(null)))
                .collect(toList()))
            .map(simulacaos -> SimularPropostasResponse.builder().simulacoes(simulacaos).build()).get();

        expected.getSimulacoes().forEach(l -> {
            assertEquals(l, actual.getSimulacoes().get(expected.getSimulacoes().indexOf(l)));
        });
    }

    private void simular_propostas(final SimularPropostasResponse response) {

        final SimularPropostasRequest request = Fixture.make(SimularPropostasRequest.builder().build());
        final Person person = Fixture.make(Person.builder().build());

        when(personClient.getPersonByCpf(request.getCpf())).thenReturn(java.util.Optional.of(person));
        when(loanClient.simularPropostas(any(SimulacaoClientRequest.class))).thenReturn(response);

        try {

            simulacaoService.simularPropostas(request);
        } finally {

            verify(cpfValidator).validar(request.getCpf());
            verify(personClient).getPersonByCpf(request.getCpf());
            verify(loanClient).simularPropostas(simulacaoClientRequestArgumentCaptor.capture());
            verify(messageHelper).get(ERRO_AO_SIMULAR_PROPOSTAS);

            final SimulacaoClientRequest actualCaptor = simulacaoClientRequestArgumentCaptor.getValue();

            assertEquals(request.getCpf(), actualCaptor.getCpf());
            assertEquals(request.getValorFinanciado(), actualCaptor.getValorFinanciado());
            assertEquals(request.getDataPrimeiroVencimento(), actualCaptor.getDataPrimeiroVencimento());
            assertEquals(person.getBirthDate(), actualCaptor.getDataNascimento());
        }
    }

    @Test(expected = BusinessErrorException.class)
    public void propostas_vazias() {
        simular_propostas(SimularPropostasResponse.builder().simulacoes(Collections.emptyList())
            .build());
    }

    @Test(expected = BusinessErrorException.class)
    public void propostas_nulas() {
        simular_propostas(SimularPropostasResponse.builder().simulacoes(null)
            .build());
    }

    @Test(expected = BusinessErrorException.class)
    public void response_nulo() {
        simular_propostas(null);
    }
}