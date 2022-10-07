package br.com.calcard.loan.dto.banco;

import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = Banco.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class Banco implements Serializable {

	String codigo;
	String nome;

	@JsonPOJOBuilder(withPrefix = "")
	public static class JacksonBuilder {

	}
}
