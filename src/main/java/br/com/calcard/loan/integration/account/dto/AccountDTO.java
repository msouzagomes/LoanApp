package br.com.calcard.loan.integration.account.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;

@Value
@With
@JsonDeserialize(builder = AccountDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class AccountDTO implements Serializable {

    private static final long serialVersionUID = 4014552408883604965L;

    String cpf;
    Boolean dependent;
    Long idPersonOwner;
    Long idAccount;
    Long idPerson;
    Product product;

    @Value
    @With
    @Builder(builderClassName = "JacksonBuilder")
    @JsonDeserialize(builder = Product.JacksonBuilder.class)
    public static class Product {
        Long idProduct;
        String name;
        Boolean embossable;
        Boolean status;

        @JsonPOJOBuilder(withPrefix = "")
        public static class JacksonBuilder {
        }
    }

    Platform platform;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
