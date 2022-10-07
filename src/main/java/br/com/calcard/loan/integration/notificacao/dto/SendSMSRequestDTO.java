package br.com.calcard.loan.integration.notificacao.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = SendSMSRequestDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class SendSMSRequestDTO implements Serializable {

    String messageText;
    List<String> mobileNumbers;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}