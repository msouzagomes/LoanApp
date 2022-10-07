package br.com.calcard.loan.integration.loan.dto;

import br.com.calcard.loan.enumeration.EstadoCivil;
import br.com.calcard.loan.enumeration.Nacionalidade;
import br.com.calcard.loan.enumeration.Sexo;
import br.com.calcard.loan.util.StringUtil;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@With
@JsonDeserialize(builder = CriaClienteRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class CriaClienteRequest implements Serializable {

    @NotBlank(message = "Nome " + StringUtil.INVALIDO)
    String nome;

    @NotBlank(message = "CPF " + StringUtil.INVALIDO)
    String cpf;

    @NotBlank(message = "CEP " + StringUtil.INVALIDO)
    String cep;

    @NotBlank(message = "RG " + StringUtil.INVALIDO)
    String rg;

    @NotNull(message = "UF Documento " + StringUtil.INVALIDO)
    Uf ufDocumento;

    @NotBlank(message = "Orgão Emissor Documento " + StringUtil.INVALIDO)
    String orgaoEmissorDocumento;

    @NotNull(message = "Data Emissão Documento " + StringUtil.INVALIDO)
    LocalDateTime dataEmissaoDocumento;

    @NotBlank(message = "Logradouro " + StringUtil.INVALIDO)
    String logradouro;

    @NotBlank(message = "Número " + StringUtil.INVALIDO)
    String numero;

    @NotBlank(message = "Bairro " + StringUtil.INVALIDO)
    String bairro;

    @NotBlank(message = "Cidade " + StringUtil.INVALIDO)
    String cidade;

    @NotNull(message = "UF informado é inválida" + StringUtil.INVALIDA)
    Uf uf;

    @NotBlank(message = "Telefone " + StringUtil.INVALIDO)
    String telefone;

    @NotBlank(message = "Email " + StringUtil.INVALIDO)
    String email;

    @NotNull(message = "Data Nascimento " + StringUtil.INVALIDA)
    LocalDateTime dataNascimento;

    @NotNull(message = "Nacionalidade " + StringUtil.INVALIDA)
    Nacionalidade nacionalidade;

    @NotNull(message = "Sexo " + StringUtil.INVALIDO)
    Sexo sexo;

    @NotNull(message = "Sexo informado " + StringUtil.INVALIDO)
    BigDecimal valorRenda;

    @NotBlank(message = "Naturalidade " + StringUtil.INVALIDA)
    String naturalidade;

    String pep;

    @NotNull(message = "Estado Civil "+StringUtil.INVALIDO)
    EstadoCivil estadoCivil;

    String conjugeNome;
    String conjugeDdd;
    String conjugeTelefone;
    LocalDateTime conjugeDataNascimento;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {}

}
