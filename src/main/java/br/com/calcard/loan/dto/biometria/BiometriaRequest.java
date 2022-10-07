package br.com.calcard.loan.dto.biometria;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Value
@With
@JsonDeserialize(builder = BiometriaRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class BiometriaRequest {

    @NotBlank(message = "CPF informado é inválido.")
    String cpf;

    @Valid
    List<DocumentosAnalisadosRequest> documentos;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
