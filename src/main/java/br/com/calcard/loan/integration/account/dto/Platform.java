package br.com.calcard.loan.integration.account.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Platform {
    PCPL("Private Label"),
    PCH("Visa"),
    ALL("Todos");

    final String value;
}
