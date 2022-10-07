package br.com.calcard.loan.integration.exception;

import java.util.List;

import br.com.calcard.loan.dto.Error;
import br.com.calcard.loan.dto.ErrorCode;
import br.com.calcard.loan.exception.AbstractErrorException;
import lombok.Getter;

@Getter
public class IntegrationErrorException extends AbstractErrorException {

    public IntegrationErrorException(ErrorCode errorCode, String reason) {
        super(errorCode.getHttpStatus(), reason, errorCode.getCode());
    }

    public IntegrationErrorException(ErrorCode errorCode, String reason, List<Error> errors) {
        super(errorCode.getHttpStatus(), reason, errorCode.getCode(), errors);
    }
}