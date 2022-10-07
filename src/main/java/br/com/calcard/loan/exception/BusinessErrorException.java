package br.com.calcard.loan.exception;

import java.util.List;

import br.com.calcard.loan.dto.Error;
import br.com.calcard.loan.dto.ErrorCode;

public class BusinessErrorException extends AbstractErrorException {

    public BusinessErrorException(ErrorCode errorCode, String reason) {
        super(errorCode.getHttpStatus(), reason, errorCode.getCode());
    }

    public BusinessErrorException(ErrorCode errorCode, String reason, List<Error> errors) {
        super(errorCode.getHttpStatus(), reason, errorCode.getCode(), errors);
    }
}
