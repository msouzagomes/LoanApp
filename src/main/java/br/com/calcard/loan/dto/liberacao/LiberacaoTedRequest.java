package br.com.calcard.loan.dto.liberacao;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotBlank;

@Value
@With
@JsonDeserialize(builder = LiberacaoTedRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class LiberacaoTedRequest {

    @NotBlank(message = "BANCO informado é inválido.")
    String banco;

    @NotBlank(message = "AGENCIA informado é inválido.")
    String agencia;

    @NotBlank(message = "TIPO DE CONTA informado é inválido.")
    String tipoConta;

    @NotBlank(message = "CONTA informado é inválido.")
    String conta;

    @NotBlank(message = "DIGITO informado é inválido.")
    String digito;

    @NotBlank(message = "CPF informado é inválido.")
    String cpf;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }

}
