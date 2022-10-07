package br.com.calcard.loan.config;

import static org.springframework.http.HttpMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import br.com.calcard.securityresource.config.SecurityResourceConfigurer;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
public class SecurityConfig extends SecurityResourceConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    public void configure(HttpSecurity http) throws Exception {
        LOGGER.debug("-- Security Configuration CUSTOM --");
        http.csrf().disable()
            .cors().and()
            .authorizeRequests()
            .antMatchers(GET,
                "/**/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/docs/**",
                "/webjars/**",
                "favicon.ico")
            .permitAll()
            .antMatchers("/health",
                "/metrics",
                    "/**/valida-email")
        .permitAll()
        .antMatchers("/**").hasAuthority("CREDIT")
        .antMatchers(HttpMethod.OPTIONS).permitAll();
    }

}
