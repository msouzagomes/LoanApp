package br.com.calcard.loan.service.simulacao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import br.com.calcard.loan.dto.simulacao.Simulacao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimulacaoEntityTest {

    @Test
    public void createSimulacaoDTO() {
        final LocalDateTime date = LocalDateTime.now();

        final Simulacao simulacao = Simulacao.builder()
            .valorSolicitado(BigDecimal.valueOf(1000))
            .valorFinanciado(BigDecimal.valueOf(1100))
            .impostosOperacao(BigDecimal.valueOf(100))
            .jurosMensal(BigDecimal.valueOf(7.99))
            .tarifaCredito(BigDecimal.valueOf(100))
            .valorParcelas(BigDecimal.valueOf(197.93))
            .parcelas(List.of(
                Simulacao.Parcela.builder().id(0).valor(BigDecimal.valueOf(197.93)).build(),
                Simulacao.Parcela.builder().id(1).valor(BigDecimal.valueOf(197.93)).build(),
                Simulacao.Parcela.builder().id(2).valor(BigDecimal.valueOf(197.93)).build(),
                Simulacao.Parcela.builder().id(3).valor(BigDecimal.valueOf(197.93)).build(),
                Simulacao.Parcela.builder().id(4).valor(BigDecimal.valueOf(197.93)).build(),
                Simulacao.Parcela.builder().id(5).valor(BigDecimal.valueOf(197.93)).build(),
                Simulacao.Parcela.builder().id(6).valor(BigDecimal.valueOf(197.93)).build(),
                Simulacao.Parcela.builder().id(7).valor(BigDecimal.valueOf(197.93)).build(),
                Simulacao.Parcela.builder().id(8).valor(BigDecimal.valueOf(197.93)).build(),
                Simulacao.Parcela.builder().id(9).valor(BigDecimal.valueOf(197.93)).build()
                )
            )
            .build();

        log.info("Criando objeto de simulação", simulacao);

        assertEquals(simulacao.getValorSolicitado(), BigDecimal.valueOf(1000));
        assertEquals(simulacao.getValorFinanciado(), BigDecimal.valueOf(1100));
        assertEquals(simulacao.getImpostosOperacao(), BigDecimal.valueOf(100));
        assertEquals(simulacao.getJurosMensal(), BigDecimal.valueOf(7.99));
        assertEquals(simulacao.getTarifaCredito(), BigDecimal.valueOf(100));
        assertEquals(simulacao.getValorParcelas(), BigDecimal.valueOf(197.93));
        assertEquals(simulacao.getValorFinanciado(), BigDecimal.valueOf(1100));


    }
}
