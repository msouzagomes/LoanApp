package br.com.calcard.loan.resource.v1.banco;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calcard.loan.dto.banco.ConsultarListaBancosResponse;
import br.com.calcard.loan.service.banco.BancoService;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/banco")
public class BancoResource {

	private final BancoService bancoService;

	@GetMapping("/lista")
	public ConsultarListaBancosResponse consultarListaBancos() {
		return bancoService.consultarListaBancos();
	}
}
