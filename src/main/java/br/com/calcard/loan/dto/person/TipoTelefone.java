package br.com.calcard.loan.dto.person;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TipoTelefone {

    CELULAR(18),
    EX_CELULAR(19);

    private final int value;
}

