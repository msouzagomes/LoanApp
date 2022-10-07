package br.com.calcard.loan.dto.biometria;

import br.com.calcard.loan.dto.documento.TipoDocumento;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Value
@With
@JsonDeserialize(builder = DocumentosAnalisadosRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class DocumentosAnalisadosRequest {

    @Valid
    TipoDocumento tipoDocumento;

    @NotBlank(message = "Imagem em formato Base64 deve ser informada.")
    String base64;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
