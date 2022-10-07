package br.com.calcard.loan.dto.person;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;

@Value
@With
@JsonDeserialize(builder = PersonDetailResponseDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class PersonDetailResponseDTO implements Serializable {

    Long personId;
    Long scholarity;
    Long dependents;
    Long professionId;
    Long nationalityId;
    Long maritalStatus;
    Long ocupationOrigemId;
    String email;
    String birthCity;
    String bankNumber;
    String birthState;
    String fatherName;
    String motherName;
    String companyName;
    String agencyNumber;
    String referencyName;
    String referency2Name;
    String referencyAddress;
    String referency2Address;
    String currentAccountNumber;
    Boolean overdraft;
    Boolean financingBlocked;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {}

}
