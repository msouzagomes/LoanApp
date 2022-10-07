package br.com.calcard.loan.integration.person;

import br.com.calcard.loan.dto.person.Person;
import br.com.calcard.loan.dto.person.PersonDetailResponseDTO;
import br.com.calcard.loan.integration.config.FeignConfigBasicAuth;
import br.com.calcard.loan.integration.config.FeignIntegrationConfig;
import br.com.calcard.loan.integration.person.dto.AddressDTO;
import br.com.calcard.loan.integration.person.dto.TelephoneResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "PersonClient", url = "${api.path.person.host}${api.path.person.basePath}", configuration = {
    FeignIntegrationConfig.class, FeignConfigBasicAuth.class})
public interface PersonClient {

    @GetMapping(value = "${api.path.person.person.getPersonByCpf}")
    Optional<Person> getPersonByCpf(@RequestParam("cpf") String cpf);

    @GetMapping(value = "${api.path.person.person.basePath}${api.path.person.person.telephone}")
    List<TelephoneResponseDTO> getTelephone(@PathVariable("id") Long id);

    @GetMapping(value = "${api.path.person.person.basePath}${api.path.person.person.detail}")
    Optional<PersonDetailResponseDTO> getPersonDetailByCpf(@RequestParam("cpf") String cpf);

    @GetMapping(value = "${api.path.person.person.address}")
    Page<AddressDTO> getAddressQuery(
            @RequestParam(value = "personId") Long personId);

}
