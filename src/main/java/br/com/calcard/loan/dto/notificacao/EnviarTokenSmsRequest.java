package br.com.calcard.loan.dto.notificacao;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = EnviarTokenSmsRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class EnviarTokenSmsRequest {

    @NotBlank(message = "CPF informado é inválido.")
    String cpf;

    @NotBlank(message = "Telefone informado é inválido.")
    String telefone;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
