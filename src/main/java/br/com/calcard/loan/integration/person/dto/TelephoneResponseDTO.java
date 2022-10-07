package br.com.calcard.loan.integration.person.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = TelephoneResponseDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class TelephoneResponseDTO {

    Long idTelephoneType;
    String areaCode;
    String telephone;
    String extensionLine;
    Boolean status;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {}

}
