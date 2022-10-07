package br.com.calcard.loan.dto.store;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;

@Value
@With
@JsonDeserialize(builder = CurrentStoreResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class CurrentStoreResponse implements Serializable {

    String code;
    String idSap;
    String name;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
