package br.com.calcard.loan.dto.documento;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;
import java.util.List;

@Value
@With
@JsonDeserialize(builder = TipoDocumentoResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class TipoDocumentoResponse implements Serializable {

    List<TipoDocumento> tiposDocumentos;


    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
