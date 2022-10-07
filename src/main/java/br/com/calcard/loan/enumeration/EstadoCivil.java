package br.com.calcard.loan.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoCivil {

    NAO_DEFINIDO("NaoDefinido"),
    SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    DESQUITADO("Desquitado"),
    DIVORCIADO("Divorciado"),
    VIUVO("Viuvo"),
    OUTROS("Outros");

    final String value;
}
