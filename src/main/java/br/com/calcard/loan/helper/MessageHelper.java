package br.com.calcard.loan.helper;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import br.com.calcard.loan.dto.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MessageHelper {

    private final MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public String get(String code, Object... args) {
        return accessor.getMessage(code, args);
    }

    public String get(ErrorCode code, Object... args) {
        return accessor.getMessage(code.getMessageKey(), args);
    }
}
