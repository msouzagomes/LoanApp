package br.com.calcard.loan.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CrivoStatusResultEnum {

    APROVADA(1, "APROVADA", "A"),
    NEGADA(2, "NEGADA", "R"),
    PENDENTE(3, "PENDENTE", "P");

    private final Integer id;
    private final String code;
    private final String situation;

}

