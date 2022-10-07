package br.com.calcard.loan.dto.limite;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = ContratoAtivo.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ContratoAtivo implements Serializable {

    String numero;

    LocalDate dataProximaParcela;

    SituacaoOperacao situacao;

    LocalDate dataLiquidacao;

    BigDecimal valorCredito;

    Long quantidadeParcelasPagas;

    Long quantidadeParcelas;

    BigDecimal valorParcela;

    BigDecimal saldoDevedor;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}