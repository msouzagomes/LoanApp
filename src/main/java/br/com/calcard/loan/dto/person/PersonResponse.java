package br.com.calcard.loan.dto.person;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;

@Value
@With
@JsonDeserialize(builder = PersonResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class PersonResponse {

    Long id;
    String cpf;
    String name;
    String telephone;
    String email;
    LocalDate dataNascimento;
    String rg;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {}

}
