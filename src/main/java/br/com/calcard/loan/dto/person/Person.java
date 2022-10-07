package br.com.calcard.loan.dto.person;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = Person.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class Person implements Serializable {

    Long id;
    Long idPch;
    Long idPcpl;
    String name;
    String type;
    String cpf;
    LocalDate birthDate;
    String gender;
    String rgNumber;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {}

}
