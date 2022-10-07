package br.com.calcard.loan.dto.liberacao;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = LiberacaoTedResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class LiberacaoTedResponse {


    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
