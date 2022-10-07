package br.com.calcard.loan.dto;

import static br.com.calcard.loan.dto.Code.CODE_0001;
import static br.com.calcard.loan.dto.Code.CODE_0002;
import static br.com.calcard.loan.dto.Code.CODE_0003;
import static br.com.calcard.loan.dto.Code.CODE_0004;
import static br.com.calcard.loan.dto.Code.CODE_0005;
import static br.com.calcard.loan.dto.Code.CODE_0006;
import static br.com.calcard.loan.dto.Code.CODE_0007;
import static br.com.calcard.loan.dto.Code.CODE_0008;
import static br.com.calcard.loan.dto.Code.CODE_0009;
import static br.com.calcard.loan.dto.Code.CODE_0010;
import static br.com.calcard.loan.dto.Code.CODE_0011;
import static br.com.calcard.loan.dto.Code.CODE_0012;
import static br.com.calcard.loan.dto.Code.CODE_0013;
import static br.com.calcard.loan.dto.Code.CODE_0014;
import static br.com.calcard.loan.dto.Code.CODE_0015;
import static br.com.calcard.loan.dto.Code.CODE_0016;
import static br.com.calcard.loan.dto.Code.CODE_0017;
import static br.com.calcard.loan.dto.Code.CODE_0018;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {

    CEP_NAO_ENCONTRADO("cep.nao.encontrado", CODE_0010.getCode(), NOT_ACCEPTABLE),
    CLIENTE_NAO_ENCONTRADO("cliente.nao.encontrado", CODE_0002.getCode(), NOT_ACCEPTABLE),
    CPF_INVALIDO("cpf.invalido", CODE_0007.getCode(), NOT_ACCEPTABLE),
    DOCUMENTOS_NAO_ENCONTRADOS_NO_DIRETORIO("documentos.nao.encontrados.diretorio", CODE_0013.getCode(),
        NOT_ACCEPTABLE),
    EMPRESTIMO_INDISPONIVEL("emprestimo.indisponivel", CODE_0009.getCode(), NOT_ACCEPTABLE),
    ERRO_AO_BUSCAR_DOCUMENTO_NO_DIRETORIO("erro.buscar.documento.diretorio", CODE_0012.getCode(), NOT_ACCEPTABLE),
    ERRO_AO_SIMULAR_PROPOSTAS("erro.simular.propostas", CODE_0008.getCode(), NOT_ACCEPTABLE),
    ERRO_GERACAO_TOKEN("erro.geracao.token", CODE_0016.getCode(), NOT_ACCEPTABLE),
    ERRO_INTERNO("erro.interno", CODE_0006.getCode(), INTERNAL_SERVER_ERROR),
    ERRO_INTERNO_INTEGRACAO("erro.interno.integracao", CODE_0004.getCode(), INTERNAL_SERVER_ERROR),
    INTEGRACAO_NAO_AUTORIZADO("integracao.nao.autorizado", CODE_0003.getCode(), INTERNAL_SERVER_ERROR),
    LOJA_NAO_ENCONTRADA("loja.nao.encontrada", CODE_0005.getCode(), NOT_ACCEPTABLE),
    MENSAGEM_TOKENIZACAO_NAO_ENCONTRADA("mensagem.token.nao.encontrada", CODE_0018.getCode(), NOT_ACCEPTABLE),
    PROMOTORES_NAO_ENCONTRADOS("promotores.nao.encontrados", CODE_0001.getCode(), NOT_ACCEPTABLE),
    STORAGE_NAO_ENCONTRADO("storage.nao.encontrado", CODE_0014.getCode(), NOT_ACCEPTABLE),
    TIPOS_DOCUMENTOS_NAO_ENCONTRADOS("tipos.documentos.nao.encontrados", CODE_0011.getCode(), NOT_ACCEPTABLE),
    AGENCIA_NAO_ENCONTRADA("agencia.nao.encontrada", CODE_0015.getCode(), NOT_ACCEPTABLE),
    TOKEN_INVALIDO("token.invalido", CODE_0017.getCode(), NOT_ACCEPTABLE),
    EMAIL_NAO_VALIDADO("email.nao.validado", CODE_0018.getCode(), NOT_ACCEPTABLE),
    LIBERACAO_INVALIDO("liberacao.ted.invalido", CODE_0017.getCode(), NOT_ACCEPTABLE);


    private final String messageKey;
    private final String code;
    private final HttpStatus httpStatus;
}
