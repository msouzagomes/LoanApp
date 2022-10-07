package br.com.calcard.loan.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

import br.com.calcard.loan.dto.Error;
import br.com.calcard.loan.dto.ErrorCode;

public class InternalErrorException extends AbstractErrorException {

    public InternalErrorException(ErrorCode errorCode, String reason) {
        super(INTERNAL_SERVER_ERROR, reason, errorCode.getCode());
    }

    public InternalErrorException(ErrorCode errorCode, String reason, List<Error> errors) {
        super(INTERNAL_SERVER_ERROR, reason, errorCode.getCode(), errors);
    }
}