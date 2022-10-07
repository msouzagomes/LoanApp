package br.com.calcard.loan.util;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import org.apache.commons.text.WordUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    public final static String INVALIDO="informado é inválido";
    public final static String INVALIDA="informada é inválida";


    public static String capitalizeFully(String name) {
        return ofNullable(name)
            .map(WordUtils::capitalizeFully)
            .orElse(EMPTY);
    }


}