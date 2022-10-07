package br.com.calcard.loan.integration.loan.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = ConsultarEnderecoClientResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ConsultarEnderecoClientResponse implements Serializable {

    String cep;

    String logradouro;

    String bairro;

    String cidade;

    Uf uf;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}