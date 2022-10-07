package br.com.calcard.loan.service.promoter;

import static br.com.calcard.loan.dto.ErrorCode.PROMOTORES_NAO_ENCONTRADOS;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.BooleanUtils.isTrue;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.calcard.loan.annotation.Auditoria;
import br.com.calcard.loan.builder.PromoterBuilder;
import br.com.calcard.loan.dto.store.Promoter;
import br.com.calcard.loan.dto.store.PromotersResponse;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.store.StoreClient;
import br.com.calcard.loan.integration.store.dto.CurrentStoreDTO;
import br.com.calcard.loan.integration.store.dto.PromoterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromoterService {

    private final StoreClient storeClient;
    private final MessageHelper messageHelper;

    @Auditoria
    public PromotersResponse getPromotersByIp(final String ip) {

        log.info("Consultando promotores da loja pelo ip {}", ip);
        final CurrentStoreDTO store = storeClient.getStoreByIp(ip);

        final List<PromoterDTO> promotersList = ofNullable(storeClient.getPromotersByIdStore(store.getId()))
            .map(list -> list.stream()
                .filter(promotor -> isTrue(promotor.getEnabled())))
            .map(list -> list.collect(toList()))
            .filter(list -> isFalse(list.isEmpty()))
            .orElseThrow(() -> new BusinessErrorException(PROMOTORES_NAO_ENCONTRADOS,
                messageHelper.get(PROMOTORES_NAO_ENCONTRADOS, store.getPcName())));

        final List<Promoter> list = promotersList.stream()
            .map(PromoterBuilder::buildPromoter)
            .sorted(Comparator.comparing(Promoter::getName))
            .collect(toList());

        return PromotersResponse.builder()
            .list(list)
            .build();
    }
}
