package br.com.calcard.loan.builder;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.EMPTY;

import java.util.regex.Pattern;

import br.com.calcard.loan.dto.person.Person;
import br.com.calcard.loan.dto.person.PersonDetailResponseDTO;
import br.com.calcard.loan.dto.person.PersonResponse;
import br.com.calcard.loan.integration.person.dto.TelephoneResponseDTO;

public class PersonResponseBuilder {

    private static final Pattern LPAD_ZERO_PATTERN = Pattern.compile("^0+(?!$)");

    public static PersonResponse buildPersonResponseWithTelephone(final Person person,
        final PersonDetailResponseDTO personDetail,
        final TelephoneResponseDTO telephone) {
        return PersonResponse.builder()
            .cpf(person.getCpf())
            .id(person.getId())
            .name(person.getName())
            .telephone(ofNullable(telephone)
                .map(PersonResponseBuilder::getTelephone)
                .orElse(EMPTY))
            .dataNascimento(person.getBirthDate())
            .email(personDetail.getEmail())
            .build();
    }

    private static String getTelephone(final TelephoneResponseDTO telephone) {
        return ofNullable(telephone.getAreaCode())
            .orElse("")
            .replaceFirst(LPAD_ZERO_PATTERN.pattern(), "")
            .concat(telephone.getTelephone());
    }
}
