package br.com.calcard.loan.dto.limite;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;

@Value
@With
@JsonDeserialize(builder = ConsultarLimiteResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ConsultarLimiteResponse implements Serializable {

    ContratoAtivo contratoAtivo;
    LimiteResponse limiteResponse;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}