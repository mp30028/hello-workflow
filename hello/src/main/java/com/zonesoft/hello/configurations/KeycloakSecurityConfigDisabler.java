package com.zonesoft.hello.configurations;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ConditionalOnProperty(name = "keycloak.enabled", havingValue = "false", matchIfMissing = false)
public class KeycloakSecurityConfigDisabler {


	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity        	
            .authorizeHttpRequests()            
            .anyRequest()
            .permitAll();
       return httpSecurity.build();
    }
}
