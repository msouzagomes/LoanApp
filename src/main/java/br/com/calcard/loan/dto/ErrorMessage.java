package br.com.calcard.loan.dto;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = ErrorMessage.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ErrorMessage {

    @Builder.Default
    LocalDateTime timestamp = now();
    String code;
    String message;
    List<Error> errors;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}