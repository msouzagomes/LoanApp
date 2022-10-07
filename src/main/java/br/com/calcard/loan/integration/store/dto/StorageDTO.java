package br.com.calcard.loan.integration.store.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = StorageDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class StorageDTO {

    Integer id;
    String description;
    String password;
    String path;
    String username;
    Boolean status;
    Integer ttlDays;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder { }
}
