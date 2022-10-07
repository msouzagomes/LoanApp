package br.com.calcard.loan.service.store;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.calcard.loan.annotation.Auditoria;
import br.com.calcard.loan.dto.store.CurrentStoreResponse;
import br.com.calcard.loan.integration.store.StoreClient;
import br.com.calcard.loan.integration.store.dto.CurrentStoreDTO;
import lombok.SneakyThrows;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    @InjectMocks
    private StoreService service;

    @Mock
    private StoreClient storeClient;

    @Test
    @SneakyThrows
    public void annotation_auditoria() {
        assertTrue(service.getClass().getDeclaredMethod("getCurrentStoreByIp", String.class)
            .isAnnotationPresent(Auditoria.class));
    }

    @Test
    public void get_current_store_ok() {

        String ip = randomAlphabetic(10);
        CurrentStoreDTO dto = CurrentStoreDTO.builder().code(randomAlphabetic(10)).pcName(randomAlphabetic(10))
            .idSap(randomAlphabetic(10)).build();

        when(storeClient.getStoreByIp(ip)).thenReturn(dto);

        CurrentStoreResponse response = service.getCurrentStoreByIp(ip);

        verify(storeClient).getStoreByIp(ip);

        assertEquals(dto.getCode(), response.getCode());
        assertEquals(dto.getPcName(), response.getName());
        assertEquals(dto.getIdSap(), response.getIdSap());
    }

}