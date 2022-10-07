package br.com.calcard.loan.dto.endereco;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;

@Value
@With
@JsonDeserialize(builder = ConsultarEnderecoResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ConsultarEnderecoResponse implements Serializable {

    String cep;

    String logradouro;

    String bairro;

    String cidade;

    String estado;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
