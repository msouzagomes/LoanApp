package br.com.calcard.loan.builder;

import static br.com.calcard.loan.util.StringUtil.capitalizeFully;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

import br.com.calcard.loan.dto.store.Promoter;
import br.com.calcard.loan.integration.store.dto.PromoterDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PromoterBuilder {

    public static Promoter buildPromoter(PromoterDTO promoterDTO) {
        return Promoter.builder()
            .login(promoterDTO.getLogin())
            .name(buildName(promoterDTO))
            .id(promoterDTO.getId())
            .build();
    }

    private static String buildName(PromoterDTO promoterDTO) {
        return capitalizeFully(promoterDTO.getFirstName() + ofNullable(promoterDTO.getLastName())
            .map(lastName -> SPACE + lastName)
            .orElse(EMPTY));
    }
}
