package br.com.calcard.loan.integration.notificacao;

import br.com.calcard.loan.integration.notificacao.dto.SendEmailRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.calcard.loan.integration.config.FeignConfigBasicAuth;
import br.com.calcard.loan.integration.config.FeignIntegrationConfig;
import br.com.calcard.loan.integration.notificacao.dto.SendSMSRequestDTO;

@FeignClient(name = "NotificacaoClient", url = "${api.path.notificacao.host}${api.path.notificacao.basePath}", configuration = {
    FeignIntegrationConfig.class, FeignConfigBasicAuth.class})
public interface NotificacaoClient {

    @PostMapping(value = "${api.path.notificacao.sms.basePath}${api.path.notificacao.sms.send}",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendSMS(@RequestBody SendSMSRequestDTO smsRequest);

    @PostMapping(value = "${api.path.notificacao.email.basePath}${api.path.notificacao.email.send}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendEmail(@RequestBody SendEmailRequestDTO emailRequest);
}
