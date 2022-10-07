package br.com.calcard.loan.service.promoter;

import static br.com.calcard.loan.util.StringUtil.capitalizeFully;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.calcard.loan.annotation.Auditoria;
import br.com.calcard.loan.dto.store.PromotersResponse;
import br.com.calcard.loan.fixture.Fixture;
import br.com.calcard.loan.integration.store.StoreClient;
import br.com.calcard.loan.integration.store.dto.CurrentStoreDTO;
import br.com.calcard.loan.integration.store.dto.PromoterDTO;
import lombok.SneakyThrows;

@RunWith(MockitoJUnitRunner.class)
public class PromoterServiceTest {

    @InjectMocks
    private PromoterService service;

    @Mock
    private StoreClient storeClient;

    @Test
    @SneakyThrows
    public void annotation_auditoria() {
        assertTrue(service.getClass().getDeclaredMethod("getPromotersByIp", String.class)
            .isAnnotationPresent(Auditoria.class));
    }

    @Test
    public void get_promoters_ok() {

        final String ip = randomAlphabetic(10);
        final CurrentStoreDTO currentStoreDTO = Fixture.make(CurrentStoreDTO.builder().build());
        final PromoterDTO firstPromoter = Fixture.make(PromoterDTO.builder()).id(1L).enabled(true).build();
        final PromoterDTO secondPromoter = Fixture.make(PromoterDTO.builder()).id(2L).enabled(true).build();
        final List<PromoterDTO> list = asList(firstPromoter, secondPromoter);

        when(storeClient.getStoreByIp(ip)).thenReturn(currentStoreDTO);
        when(storeClient.getPromotersByIdStore(currentStoreDTO.getId())).thenReturn(list);

        final PromotersResponse response = service.getPromotersByIp(ip);

        list.sort(Comparator.comparing(promoterDTO -> promoterDTO.getFirstName() + SPACE + promoterDTO.getLastName()));

        verify(storeClient).getStoreByIp(ip);
        verify(storeClient).getPromotersByIdStore(currentStoreDTO.getId());

        assertTrue(response.getList().get(0).getName().startsWith(capitalizeFully(list.get(0).getFirstName())));
        assertTrue(response.getList().get(0).getName().endsWith(capitalizeFully(list.get(0).getLastName())));
        assertEquals(list.get(0).getLogin(), response.getList().get(0).getLogin());
        assertEquals(list.get(0).getId(), response.getList().get(0).getId());
        assertTrue(response.getList().get(1).getName().startsWith(capitalizeFully(list.get(1).getFirstName())));
        assertTrue(response.getList().get(1).getName().endsWith(capitalizeFully(list.get(1).getLastName())));
        assertEquals(list.get(1).getLogin(), response.getList().get(1).getLogin());
        assertEquals(list.get(1).getId(), response.getList().get(1).getId());
    }
}