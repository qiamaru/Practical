package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;


import jakarta.annotation.PreDestroy;

@Configuration
@Slf4j
public class TerminateBean {

    @PreDestroy
    public void onDestroy() {
        log.info("Spring Container is destroyed!");
    }
    
    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }
}
