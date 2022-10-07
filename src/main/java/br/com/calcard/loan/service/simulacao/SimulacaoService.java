package br.com.calcard.loan.service.simulacao;

import static br.com.calcard.loan.dto.ErrorCode.CLIENTE_NAO_ENCONTRADO;
import static br.com.calcard.loan.dto.ErrorCode.ERRO_AO_SIMULAR_PROPOSTAS;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.com.calcard.loan.annotation.Auditoria;
import br.com.calcard.loan.builder.SimulacaoClientRequestBuilder;
import br.com.calcard.loan.dto.person.Person;
import br.com.calcard.loan.dto.simulacao.SimularPropostasRequest;
import br.com.calcard.loan.dto.simulacao.SimularPropostasResponse;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.loan.LoanClient;
import br.com.calcard.loan.integration.loan.dto.SimulacaoClientRequest;
import br.com.calcard.loan.integration.person.PersonClient;
import br.com.calcard.loan.validator.CpfValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimulacaoService {

    private final PersonClient personClient;
    private final CpfValidator cpfValidator;
    private final LoanClient loanClient;
    private final MessageHelper messageHelper;

    @Auditoria
    public SimularPropostasResponse simularPropostas(final SimularPropostasRequest request) {

        cpfValidator.validar(request.getCpf());

        log.info("Simulando propostas para {}, com valor financiado escolhido de: {}", sha256Hex(request.getCpf()),
            request.getValorFinanciado());

        final LocalDate dataNascimento = personClient.getPersonByCpf(request.getCpf())
            .map(Person::getBirthDate)
            .orElseThrow(() -> new BusinessErrorException(CLIENTE_NAO_ENCONTRADO,
                messageHelper.get(CLIENTE_NAO_ENCONTRADO)));

        final SimulacaoClientRequest requestClient = SimulacaoClientRequestBuilder
            .builSimulacaoClientRequest(request, dataNascimento);

        return ofNullable(loanClient.simularPropostas(requestClient))
            .filter(list -> isNotEmpty(list.getSimulacoes()))
            .map(SimularPropostasResponse::getSimulacoes)
            .map(simulacoes -> simulacoes.stream()
                .map(simulacao -> simulacao.withTaxaCetAnual(ofNullable(simulacao.getTaxaCetAnual())
                    .map(value -> value.setScale(2, RoundingMode.HALF_EVEN))
                    .orElse(null)))
                .collect(toList()))
            .map(simulacaos -> SimularPropostasResponse.builder().simulacoes(simulacaos).build())
            .orElseThrow(() -> new BusinessErrorException(ERRO_AO_SIMULAR_PROPOSTAS,
                messageHelper.get(ERRO_AO_SIMULAR_PROPOSTAS)));
    }

}
