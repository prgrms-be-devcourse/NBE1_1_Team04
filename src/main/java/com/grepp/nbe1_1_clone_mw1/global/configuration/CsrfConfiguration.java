package com.grepp.nbe1_1_clone_mw1.global.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class CsrfConfiguration {

    @Bean
    CsrfTokenRequestAttributeHandler requestAttributeHandler() {
        CsrfTokenRequestAttributeHandler tokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        tokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");
        return tokenRequestAttributeHandler;
    }

}
