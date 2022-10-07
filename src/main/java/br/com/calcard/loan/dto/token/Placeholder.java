package br.com.calcard.loan.dto.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Placeholder {

    TOKEN("{{token}}");

    private final String valor;
}
