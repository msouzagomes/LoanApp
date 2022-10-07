package br.com.calcard.loan.dto.store;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.Serializable;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = Promoter.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class Promoter implements Serializable {

    Long id;
    String name;
    String login;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
