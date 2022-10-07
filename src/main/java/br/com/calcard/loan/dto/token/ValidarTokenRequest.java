package br.com.calcard.loan.dto.token;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = ValidarTokenRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ValidarTokenRequest {

    @NotBlank(message = "CPF informado é inválido.")
    String cpf;

    @NotBlank(message = "Telefone informado é inválido.")
    String telefone;

    @NotBlank(message = "Token informado é inválido.")
    String token;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
