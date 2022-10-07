package br.com.calcard.loan.builder;

import br.com.calcard.loan.dto.person.PersonResponse;
import br.com.calcard.loan.integration.loan.dto.AtualizaClienteRequest;
import br.com.calcard.loan.integration.loan.dto.CriaClienteRequest;
import br.com.calcard.loan.integration.loan.dto.Uf;
import br.com.calcard.loan.integration.person.dto.AddressDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ClienteBuilder {

    public static CriaClienteRequest buildCriaClienteRequest(PersonResponse personResponse, AddressDTO addressDTO) {
        return CriaClienteRequest.builder()
                .nome(personResponse.getName())
                .cpf(personResponse.getCpf())
                .rg(personResponse.getRg())
                .email(personResponse.getEmail())
                .dataNascimento(LocalDateTime.of(personResponse.getDataNascimento(),LocalTime.NOON))
                .logradouro(addressDTO.getStreet())
                .numero(addressDTO.getNumber())
                .bairro(addressDTO.getNeighborhood())
                .cep(addressDTO.getPostalCode())
                .cidade(addressDTO.getCity())
                .uf(Uf.valueOf(addressDTO.getState()))
                .telefone(personResponse.getTelephone())
                .build();
    }

    public static AtualizaClienteRequest buildAtualizaClienteRequest(PersonResponse personResponse, AddressDTO addressDTO) {
        return AtualizaClienteRequest.builder()
                .rg(personResponse.getRg())
                .email(personResponse.getEmail())
                .dataNascimento(LocalDateTime.of(personResponse.getDataNascimento(), LocalTime.NOON))
                .logradouro(addressDTO.getStreet())
                .numero(addressDTO.getNumber())
                .bairro(addressDTO.getNeighborhood())
                .cep(addressDTO.getPostalCode())
                .cidade(addressDTO.getCity())
                .uf(Uf.valueOf(addressDTO.getState()))
                .telefone(personResponse.getTelephone())
                .build();
    }
}
