package br.com.calcard.loan.resource.v1.person;

import br.com.calcard.loan.dto.person.PersonResponse;
import br.com.calcard.loan.service.person.PersonService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/person")
public class PersonResource {

    private final PersonService personService;

    @GetMapping
    @Timed
    public PersonResponse getPersonByCpf(@RequestParam(value = "cpf") String cpf) {
        return personService.getPersonByCpf(cpf);
    }

}
