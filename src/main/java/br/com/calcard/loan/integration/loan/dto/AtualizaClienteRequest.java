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
@JsonDeserialize(builder = AtualizaClienteRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class AtualizaClienteRequest implements Serializable {

    Long id;
    String nome;

    @NotBlank(message = "Codigo Cliente " + StringUtil.INVALIDO)
    String codigoCliente;

    @NotBlank(message = "CPF " + StringUtil.INVALIDO)
    String cpf;
    String cep;

    @NotBlank(message = "RG " + StringUtil.INVALIDO)
    String rg;

    @NotNull(message = "UF Documento " + StringUtil.INVALIDO)
    Uf ufDocumento;

    @NotBlank(message = "Orgão Emissor Documento " + StringUtil.INVALIDO)
    String orgaoEmissorDocumento;

    @NotNull(message = "Data Emissão Documento " + StringUtil.INVALIDO)
    LocalDateTime dataEmissaoDocumento;

    String logradouro;
    String numero;
    String bairro;
    String cidade;
    Uf uf;
    String telefone;
    String email;

    @NotNull(message = "Data Nascimento " + StringUtil.INVALIDA)
    LocalDateTime dataNascimento;

    @NotNull(message = "Nacionalidade " + StringUtil.INVALIDA)
    Nacionalidade nacionalidade;

    @NotNull(message = "Sexo " + StringUtil.INVALIDO)
    Sexo sexo;

    @NotNull(message = "Valor Renda " + StringUtil.INVALIDA)
    BigDecimal valorRenda;
    String naturalidade;

    @NotBlank(message = "PEP " + StringUtil.INVALIDO)
    String pep;

    @NotNull(message = "Estado Civil " + StringUtil.INVALIDO)
    EstadoCivil estadoCivil;

    String conjugeNome;
    String conjugeDdd;
    String conjugeTelefone;
    LocalDateTime conjugeDataNascimento;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {}
}
