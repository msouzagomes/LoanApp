package br.com.calcard.loan.integration.notificacao.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;
import java.util.List;

@Value
@With
@JsonDeserialize(builder = SendEmailRequestDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class SendEmailRequestDTO implements Serializable {

    String toEmailAddress;

    String fromEmailAddress;

    String subject;

    String messageText;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}