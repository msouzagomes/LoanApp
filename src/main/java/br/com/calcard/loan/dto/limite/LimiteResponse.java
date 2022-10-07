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
@JsonDeserialize(builder = LimiteResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class LimiteResponse implements Serializable {

    BigDecimal valorMinimo;
    BigDecimal valorMaximo;
    LocalDate dataInicialPrimeiroVencimento;
    LocalDate dataFinalPrimeiroVencimento;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }

}
