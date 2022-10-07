package br.com.calcard.loan.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Nacionalidade {

    BRASILEIRA("Brasileira"),
    ESTRANGEIRA("Estrangeira"),
    NAO_DEFINIDA("NaoDefinida");

    final String value;
}
