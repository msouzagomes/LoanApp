package br.com.calcard.loan.config.property;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.calcard.loan.dto.Ambiente;
import lombok.Getter;

@Component
@Getter
public class NotificacaoConfig {

    @Value("#{'${notificacao.sms.numerosPermitidos}'.replace(' ', '').split(',')}")
    private List<String> numerosPermitidos;

    @Value("#{'${notificacao.email.emailsPermitidos}'.replace(' ', '').split(',')}")
    private List<String> emailsPermitidos;

    @Value("${notificacao.email.emailRemetente}")
    private String emailRemetente;

    @Value("#{'${notificacao.sms.ambiente}'.replace(' ', '').split(',')}")
    private List<Ambiente> ambientes;

    @Value("${notificacao.sms.codigo}")
    private String codigo;

    @Value("${notificacao.email.subtitulo}")
    private String subtitulo;
}
