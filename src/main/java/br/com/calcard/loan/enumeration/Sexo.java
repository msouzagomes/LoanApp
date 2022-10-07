package br.com.calcard.loan.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Sexo {

    M("Masculino"),
    F("Feminino");

    final String value;
}
