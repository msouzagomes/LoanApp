package br.com.calcard.loan.integration.store.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = StoreDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class StoreDTO {

    String id;
    String idSap;
    String code;
    String pcName;
    StorageDTO storage;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder { }
}
