package br.com.calcard.loan.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CrivoPolicyEnum {

    EMPRESTIMO_PESSOAL("Calcred_Concessao__P1"),
    MASSIFICADO_BANCARIZACAO("Calcred_Bancarizacao");

    private final String value;
}
