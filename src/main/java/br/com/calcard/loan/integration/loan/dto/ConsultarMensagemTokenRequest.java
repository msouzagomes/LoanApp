package br.com.calcard.loan.integration.loan.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = ConsultarMensagemTokenRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ConsultarMensagemTokenRequest implements Serializable {

    String cpf;

    String valorTipo;

    TipoEnvioToken tipoEnvio;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}