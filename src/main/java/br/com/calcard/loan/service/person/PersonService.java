package br.com.calcard.loan.service.person;

import static br.com.calcard.loan.dto.ErrorCode.CLIENTE_NAO_ENCONTRADO;
import static br.com.calcard.loan.dto.person.TipoTelefone.CELULAR;
import static java.util.Optional.ofNullable;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.calcard.loan.annotation.Auditoria;
import br.com.calcard.loan.builder.PersonResponseBuilder;
import br.com.calcard.loan.dto.person.Person;
import br.com.calcard.loan.dto.person.PersonDetailResponseDTO;
import br.com.calcard.loan.dto.person.PersonResponse;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.person.PersonClient;
import br.com.calcard.loan.integration.person.dto.TelephoneResponseDTO;
import br.com.calcard.loan.validator.CpfValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonClient personClient;
    private final MessageHelper messageHelper;
    private final CpfValidator cpfValidator;

    @Auditoria
    public PersonResponse getPersonByCpf(final String cpf) {

        cpfValidator.validar(cpf);

        log.info("Consultando dados do cliente {}.", sha256Hex(cpf));

        final Person person = personClient.getPersonByCpf(cpf)
            .orElseThrow(() -> new BusinessErrorException(CLIENTE_NAO_ENCONTRADO,
                messageHelper.get(CLIENTE_NAO_ENCONTRADO)));

        final TelephoneResponseDTO telefoneAtual = buscarCelularAtual(person.getId());

        final PersonDetailResponseDTO personDetail = personClient.getPersonDetailByCpf(cpf)
            .orElseThrow(() -> new BusinessErrorException(CLIENTE_NAO_ENCONTRADO,
                messageHelper.get(CLIENTE_NAO_ENCONTRADO)));

        return PersonResponseBuilder.buildPersonResponseWithTelephone(person, personDetail, telefoneAtual);
    }

    public TelephoneResponseDTO buscarCelularAtual(final Long idPerson) {

        log.info("Consultando número do celular para o id da pessoa: {}.", idPerson);

        final List<TelephoneResponseDTO> listaTelefones = personClient.getTelephone(idPerson);

        return ofNullable(listaTelefones).flatMap(lista -> lista.stream()
            .filter(TelephoneResponseDTO::getStatus)
            .filter(telefone -> telefone.getIdTelephoneType() == CELULAR.getValue())
            .findFirst())
            .orElse(null);
    }

}
