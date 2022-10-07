package br.com.calcard.loan.service.store;

import br.com.calcard.loan.annotation.Auditoria;
import br.com.calcard.loan.dto.store.CurrentStoreResponse;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.store.StoreClient;
import br.com.calcard.loan.integration.store.dto.CurrentStoreDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.calcard.loan.dto.ErrorCode.LOJA_NAO_ENCONTRADA;
import static java.util.Optional.ofNullable;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {

    private final StoreClient storeClient;
    private final MessageHelper messageHelper;

    @Auditoria
    public CurrentStoreResponse getCurrentStoreByIp(String ip) {

        log.info("Consultando as informações da loja pelo ip {}", ip);

        CurrentStoreDTO dto = ofNullable(storeClient.getStoreByIp(ip))
            .orElseThrow(() -> new BusinessErrorException(LOJA_NAO_ENCONTRADA,
                messageHelper.get(LOJA_NAO_ENCONTRADA, ip)));

        return CurrentStoreResponse.builder()
            .code(dto.getCode())
            .idSap(dto.getIdSap())
            .name(dto.getPcName()).build();
    }
}
