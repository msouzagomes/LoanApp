package br.com.calcard.loan.dto.documentos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@Value
@With
@JsonDeserialize(builder = DocumentosResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class DocumentosResponse {

    List<Documento> documentos;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {}
}
