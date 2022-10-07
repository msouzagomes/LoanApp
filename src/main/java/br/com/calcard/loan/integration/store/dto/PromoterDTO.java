package br.com.calcard.loan.integration.store.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.Serializable;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = PromoterDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class PromoterDTO implements Serializable {

    String firstName;
    String lastName;
    String login;
    Long id;
    Boolean enabled;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder { }
}