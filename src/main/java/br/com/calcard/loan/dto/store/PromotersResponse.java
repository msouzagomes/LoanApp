package br.com.calcard.loan.dto.store;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = PromotersResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class PromotersResponse implements Serializable {

    List<Promoter> list;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
