package br.com.calcard.loan.dto.documentos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;

@Value
@With
@JsonDeserialize(builder = Documento.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class Documento implements Serializable {

    String name;
    String base64;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {}

}
