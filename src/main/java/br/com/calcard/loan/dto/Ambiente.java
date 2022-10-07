package br.com.calcard.loan.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Ambiente {

    DEV("DEV"),
    HML("HML"),
    PRD("PRD");

    private final String value;
}
