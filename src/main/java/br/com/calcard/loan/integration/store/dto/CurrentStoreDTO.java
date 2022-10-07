package br.com.calcard.loan.integration.store.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;

@Value
@With
@JsonDeserialize(builder = CurrentStoreDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class CurrentStoreDTO implements Serializable {

    String id;
    String idSap;
    String code;
    String pcName;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder { }
}
