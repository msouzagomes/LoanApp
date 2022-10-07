package br.com.calcard.loan.dto.simulacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = SimularPropostasRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class SimularPropostasRequest {

    @NotBlank(message = "CPF informado é inválido.")
    String cpf;

    @NotNull(message = "Valor informado é inválido.")
    BigDecimal valorFinanciado;
    
    @NotNull(message = "Data do Primeiro vencimento informada é inválida.")
    LocalDate dataPrimeiroVencimento;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
