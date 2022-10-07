package br.com.calcard.loan.integration.loan.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = SalvarTokenRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class SalvarTokenRequest implements Serializable {

    String cpf;

    String token;

    String valorTipo;

    TipoEnvioToken tipoEnvio;

    LocalDateTime validade;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}