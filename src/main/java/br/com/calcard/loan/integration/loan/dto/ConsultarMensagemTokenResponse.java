package br.com.calcard.loan.integration.loan.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = ConsultarMensagemTokenResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ConsultarMensagemTokenResponse implements Serializable {

    String mensagem;

    String token;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}