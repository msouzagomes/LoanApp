package br.com.calcard.loan.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.calcard.loan.dto.Error;
import lombok.Getter;

@Getter
public abstract class AbstractErrorException extends ResponseStatusException {

    private String code;
    private List<Error> errors;

    public AbstractErrorException(HttpStatus status, String reason, String code) {
        super(status, reason);
        this.code = code;
    }

    public AbstractErrorException(HttpStatus status, String reason, String code, List<Error> errors) {
        super(status, reason);
        this.code = code;
        this.errors = errors;
    }
}
