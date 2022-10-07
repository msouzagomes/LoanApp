package br.com.calcard.loan.integration.account;

public interface AccountIntegrationFactory {

   AccountIntegration getInstance(String platform);
}
