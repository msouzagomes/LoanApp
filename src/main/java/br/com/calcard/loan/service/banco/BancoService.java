package br.com.calcard.loan.service.banco;

import static br.com.calcard.loan.dto.ErrorCode.CEP_NAO_ENCONTRADO;
import static java.util.Optional.ofNullable;

import org.springframework.stereotype.Service;

import br.com.calcard.loan.dto.banco.ConsultarListaBancosResponse;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.loan.LoanClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BancoService {

	private final LoanClient loanClient;
	private final MessageHelper messageHelper;

	public ConsultarListaBancosResponse consultarListaBancos() {

		log.info("Consultando lista de bancos.");

		return ofNullable(loanClient.consultarListaBanco())
				.orElseThrow(() -> new BusinessErrorException(CEP_NAO_ENCONTRADO, messageHelper.get(CEP_NAO_ENCONTRADO)));
	}
}
