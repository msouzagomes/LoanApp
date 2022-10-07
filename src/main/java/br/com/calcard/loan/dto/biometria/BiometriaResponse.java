package br.com.calcard.loan.dto.biometria;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = BiometriaResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class BiometriaResponse {

    String idBiometria;


    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
