package br.com.calcard.loan.builder;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import br.com.calcard.loan.dto.endereco.ConsultarEnderecoResponse;
import br.com.calcard.loan.integration.loan.dto.ConsultarEnderecoClientResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsultarEnderecoResponseBuilder {

    public static ConsultarEnderecoResponse buildConsultarEnderecoResponse(
        final ConsultarEnderecoClientResponse response) {
        return ofNullable(response)
            .map(r -> ConsultarEnderecoResponse.builder()
                .bairro(ofNullable(r.getBairro()).orElse(EMPTY))
                .cep(ofNullable(r.getCep()).orElse(EMPTY))
                .cidade(ofNullable(r.getCidade()).orElse(EMPTY))
                .logradouro(ofNullable(r.getLogradouro()).orElse(EMPTY))
                .estado(ofNullable(r.getUf()).map(Enum::name).orElse(EMPTY))
                .build())
            .orElse(null);
    }
}
