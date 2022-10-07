package br.com.calcard.loan.integration.loan.dto;

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
@JsonDeserialize(builder = SimulacaoClientRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class SimulacaoClientRequest implements Serializable {

    String cpf;
    BigDecimal valorFinanciado;
    LocalDate dataNascimento;
    LocalDate dataPrimeiroVencimento;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
