package br.com.calcard.loan.resource.v1.notificacao;

import br.com.calcard.loan.dto.notificacao.EnviarTokenEmailRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.calcard.loan.dto.notificacao.EnviarTokenSmsRequest;
import br.com.calcard.loan.service.notification.NotificacaoService;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/notificacao")
public class NotificacaoResource {

    private final NotificacaoService notificacaoService;

    @PostMapping("/sms/token")
    public void enviarNotificacaoSms(@RequestBody @Validated final EnviarTokenSmsRequest request) {
        notificacaoService.enviarSms(request);
    }

    @PostMapping("/email/token")
    public void enviarNotificacaoEmail(@RequestBody @Validated final EnviarTokenEmailRequest request) {
        notificacaoService.enviarEmail(request);
    }
}
