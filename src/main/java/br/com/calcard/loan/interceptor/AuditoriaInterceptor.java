package br.com.calcard.loan.interceptor;

import static br.com.calcard.loan.dto.RequestHeaders.CPF;
import static br.com.calcard.loan.dto.RequestHeaders.ID_PROMOTOR;
import static br.com.calcard.loan.dto.RequestHeaders.IP;
import static java.util.Optional.ofNullable;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.calcard.audit.service.CalcardAuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditoriaInterceptor {

    private final CalcardAuditService calcardAuditService;
    private final HttpServletRequest servletRequest;

    @Value("${api.audit.collection.name}")
    private String collection;

    @AfterReturning(value = "@annotation(br.com.calcard.loan.annotation.Auditoria)", returning = "returnValue")
    public void intercept(JoinPoint joinPoint, Object returnValue) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        try {

            Map<Object, Object> map = new HashMap<>();

            ofNullable(joinPoint.getArgs()).ifPresent(request -> map.put("request", request));
            ofNullable(returnValue).ifPresent(response -> map.put("response", response));
            map.put("requestDate", new Date());
            map.put("methodShortName", method.getName());
            map.put("collectionName", collection);
            map.put("authentication", SecurityContextHolder.getContext().getAuthentication());
            ofNullable(servletRequest.getHeader(IP)).ifPresent(ip -> map.put("ipLoja", ip));
            ofNullable(servletRequest.getHeader(CPF)).ifPresent(usuario -> map.put("cpfCliente", usuario));
            ofNullable(servletRequest.getHeader(ID_PROMOTOR)).ifPresent(promotor -> map.put("idPromotor", promotor));

            calcardAuditService.audit(map, false);
        } catch (Exception e) {
            log.debug("Ocorreu um erro ao realizar a auditoria do método {}", method.getName());
        }
    }
}
