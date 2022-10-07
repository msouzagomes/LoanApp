package br.com.calcard.loan.integration.person.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = AddressDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class AddressDTO {

    Long id;
    Long idPerson;
    String postalCode;
    String street;
    String number;
    String complement;
    String neighborhood;
    String city;
    String state;
    Boolean status;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
