package br.com.calcard.loan.builder;

import br.com.calcard.loan.dto.person.PersonResponse;
import br.com.calcard.loan.integration.loan.dto.CanalEnum;
import br.com.calcard.loan.integration.loan.dto.ConsultarLimiteClientRequest;
import br.com.calcard.loan.integration.store.dto.CurrentStoreDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsultaLimiteRequestBuilder {

    public static ConsultarLimiteClientRequest buildConsultaLimiteRequest(final PersonResponse person,
        final CurrentStoreDTO currentStoreDTO, final boolean anuidade) {
        return ConsultarLimiteClientRequest.builder()
            .idPessoa(person.getId())
            .cpf(person.getCpf())
            .idSap(currentStoreDTO.getIdSap())
            .codigoLoja(currentStoreDTO.getCode())
            .canal(CanalEnum.CALSYSTEM)
            .anuidade(anuidade)
            .build();
    }
}
