package br.com.calcard.loan.integration.config;

import static br.com.calcard.loan.dto.ErrorCode.EMPRESTIMO_INDISPONIVEL;
import static br.com.calcard.loan.dto.ErrorCode.ERRO_INTERNO_INTEGRACAO;
import static br.com.calcard.loan.dto.ErrorCode.INTEGRACAO_NAO_AUTORIZADO;
import static feign.Request.HttpMethod.GET;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static java.lang.System.lineSeparator;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.singletonList;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.calcard.loan.dto.Error;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.exception.IntegrationErrorException;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FeignIntegrationConfig {

    private final String REASON = "reason";
    private final MessageHelper messageHelper;

    @Bean
    ErrorDecoder errorDecoder() {
        return (s, response) -> {
            final HttpStatus responseStatus = HttpStatus.valueOf(response.status());
            final String responseMessage = getMessage(response);
            logHttpError(response, responseMessage, responseStatus);
            return Match(response).of(
                Case($(res -> GET.equals(res.request().httpMethod()) && HttpStatus.valueOf(res.status())
                        .is5xxServerError()),
                    new RetryableException(
                        responseStatus.value(),
                        responseMessage,
                        response.request().httpMethod(),
                        null,
                        response.request()
                    )
                ),
                Case($(res -> responseStatus == UNAUTHORIZED),
                    new IntegrationErrorException(INTEGRACAO_NAO_AUTORIZADO,
                        messageHelper.get(INTEGRACAO_NAO_AUTORIZADO),
                        singletonList(Error.builder()
                            .field(REASON)
                            .message(responseMessage)
                            .build()))),
                Case($(res -> responseStatus == NOT_ACCEPTABLE || responseStatus == NOT_FOUND),
                    new BusinessErrorException(EMPRESTIMO_INDISPONIVEL,
                        messageHelper.get(EMPRESTIMO_INDISPONIVEL),
                        singletonList(Error.builder()
                            .field(REASON)
                            .message(responseMessage)
                            .build()))),
                Case($(),
                    new BusinessErrorException(ERRO_INTERNO_INTEGRACAO, messageHelper.get(ERRO_INTERNO_INTEGRACAO),
                        singletonList(Error.builder()
                            .field(REASON)
                            .message(responseMessage)
                            .build())))
            );
        };
    }

    private static String getMessage(final Response response) {
        return Try.withResources(() -> response.body().asReader(UTF_8))
            .of(IOUtils::toString)
            .getOrElse(EMPTY);
    }

    private static void logHttpError(final Response response, final String responseMessage,
        final HttpStatus responseStatus) {
        ofNullable(responseStatus).ifPresent(status -> {
            final Request request = response.request();
            if (HttpStatus.BAD_REQUEST.equals(status)) {
                log.warn(buildLogFullMessage(),
                    request.httpMethod() + " " + request.url(),
                    new String(ofNullable(request.body()).orElse(EMPTY.getBytes()), UTF_8),
                    responseMessage);
            } else if (status.is5xxServerError()) {
                log.error(buildLogFullMessage(),
                    request.httpMethod() + " " + request.url(),
                    new String(ofNullable(request.body()).orElse(EMPTY.getBytes()), UTF_8),
                    responseMessage);
            }
        });
    }

    private static String buildLogFullMessage() {
        return lineSeparator() +
            "RequestUrl: {}" + lineSeparator() +
            "RequestBody: {}" + lineSeparator() +
            "Response: {}" + lineSeparator();
    }

    @Bean
    public PageJacksonModule pageJacksonModule() {
        return new PageJacksonModule();
    }

    @Bean
    Encoder jacksonEncoder(final PageJacksonModule pageModule, final ObjectMapper mapper) {
        mapper.registerModule(pageModule);
        return new JacksonEncoder(mapper);
    }

    @Bean
    Decoder jacksonDecoder(final PageJacksonModule pageModule, final ObjectMapper mapper) {
        mapper.registerModule(pageModule);
        return new JacksonDecoder(mapper);
    }

    @Bean
    Retryer retryer(@Value("${feign.client.retryer.period:1000}") final long period,
        @Value("${feign.client.retryer.max-period:5000}") final long maxPeriod,
        @Value("${feign.client.retryer.max-attempts:3}") final int maxAttempts) {
        return new CustomRetryer(period, maxPeriod, maxAttempts);
    }
}
