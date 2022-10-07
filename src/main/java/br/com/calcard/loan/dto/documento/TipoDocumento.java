package br.com.calcard.loan.dto.documento;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Value
@With
@JsonDeserialize(builder = TipoDocumento.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class TipoDocumento implements Serializable {

    Long id;

    @NotBlank(message = "Identificador informado é inválido.")
    String identificador;

    String descricao;


    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
