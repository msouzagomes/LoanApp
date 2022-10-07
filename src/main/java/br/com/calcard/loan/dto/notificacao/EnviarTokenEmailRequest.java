package br.com.calcard.loan.dto.notificacao;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Value
@With
@JsonDeserialize(builder = EnviarTokenEmailRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class EnviarTokenEmailRequest implements Serializable {

    @NotEmpty(message = "CPF informado é inválido.")
    String cpf;

    @NotEmpty(message = "Email informado é inválido.")
    String email;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
