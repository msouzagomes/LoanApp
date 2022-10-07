package br.com.calcard.loan.integration.config;

import static java.util.Collections.singletonList;

import java.util.concurrent.TimeUnit;

import br.com.calcard.loan.dto.Error;
import br.com.calcard.loan.dto.ErrorCode;
import br.com.calcard.loan.integration.exception.IntegrationErrorException;
import feign.RetryableException;
import feign.Retryer;

public class CustomRetryer implements Retryer {

    private final int maxAttempts;

    private final long period;

    private final long maxPeriod;

    int attempt;
    long sleptForMillis;

    public CustomRetryer() {
        this(100L, TimeUnit.SECONDS.toMillis(1L), 5);
    }

    public CustomRetryer(final long period, final long maxPeriod, final int maxAttempts) {
        this.period = period;
        this.maxPeriod = maxPeriod;
        this.maxAttempts = maxAttempts;
        this.attempt = 1;
    }

    protected long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public void continueOrPropagate(final RetryableException e) {
        if (this.attempt++ >= this.maxAttempts) {
            throw new IntegrationErrorException(ErrorCode.ERRO_INTERNO_INTEGRACAO,
                "Empréstimo temporariamente indisponível, tente mais tarde",
                singletonList(Error.builder()
                    .field("reason")
                    .message(e.getMessage())
                    .build()));
        } else {
            long interval;
            if (e.retryAfter() != null) {
                interval = e.retryAfter().getTime() - this.currentTimeMillis();
                if (interval > this.maxPeriod) {
                    interval = this.maxPeriod;
                }

                if (interval < 0L) {
                    return;
                }
            } else {
                interval = this.nextMaxInterval();
            }

            try {
                Thread.sleep(interval);
            } catch (final InterruptedException var5) {
                Thread.currentThread().interrupt();
                throw e;
            }

            this.sleptForMillis += interval;
        }
    }

    long nextMaxInterval() {
        final long interval = (long) ((double) this.period * Math.pow(1.5D, (double) (this.attempt - 1)));
        return interval > this.maxPeriod ? this.maxPeriod : interval;
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer(this.period, this.maxPeriod, this.maxAttempts);
    }
}
