package br.com.calcard.loan.integration.loan.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;

@Value
@With
@JsonDeserialize(builder = ConsultarLimiteClientRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ConsultarLimiteClientRequest implements Serializable {

    String cpf;
    String codigoLoja;
    CanalEnum canal;
    Long idPessoa;
    String idSap;
    Boolean anuidade;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }

}
