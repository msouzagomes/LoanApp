package br.com.calcard.loan.integration.loan.dto;

import br.com.calcard.loan.enumeration.EstadoCivil;
import br.com.calcard.loan.enumeration.Sexo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@With
@JsonDeserialize(builder = ClienteResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ClienteResponse implements Serializable {

    String codigoCliente;
    String nome;
    String cpf;
    String cep;
    String rg;
    Uf UFDocumento;
    String orgaoEmissorDocumento;
    LocalDateTime dataEmissaoDocumento;
    String logradouro;
    String numero;
    String bairro;
    String cidade;
    String estado;
    String uf;
    String telefone;
    String email;
    LocalDateTime dataNascimento;
    Sexo sexo;
    BigDecimal valorRenda;
    String naturalidade;
    String pep;
    EstadoCivil estadoCivil;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {}
}
