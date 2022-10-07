package br.com.calcard.loan.validator;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static br.com.calcard.loan.dto.ErrorCode.CPF_INVALIDO;
import static feign.Util.isBlank;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
@Component
@RequiredArgsConstructor
@Slf4j
public class CpfValidator {
    private final MessageHelper messageHelper;
    public void validar(final String cpf) {
        if (isBlank(cpf) || isFalse(new CPFValidator().invalidMessagesFor(cpf).isEmpty())) {
            log.warn("CPF inválido.");
            throw new BusinessErrorException(CPF_INVALIDO,
                    messageHelper.get(CPF_INVALIDO));
        }
    }
}