package br.com.calcard.loan.integration.config;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.oauth2.common.OAuth2AccessToken.BEARER_TYPE;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignConfigBasicAuth implements RequestInterceptor {

    @Override
    public void apply(final RequestTemplate template) {

        final String token = ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getDetails)
            .filter(details -> details instanceof OAuth2AuthenticationDetails)
            .map(details -> (OAuth2AuthenticationDetails) details)
            .map(OAuth2AuthenticationDetails::getTokenValue)
            .orElse(EMPTY);

        template.header(AUTHORIZATION, BEARER_TYPE + SPACE + token);
    }
}
