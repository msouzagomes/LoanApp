package br.com.calcard.loan.dto.banco;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = ConsultarListaBancosResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ConsultarListaBancosResponse {

	List<Banco> bancos;

	@JsonPOJOBuilder(withPrefix = "")
	public static class JacksonBuilder {

	}
}
