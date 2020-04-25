package org.debugroom.sample.spring.security.chat.config;

import org.debugroom.sample.spring.security.chat.domain.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestOperations;

@Profile("dev")
@Configuration
public class DevConfig {

    @Autowired
    ServiceProperties serviceProperties;

    @Bean
    public RestOperations restOperations(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.rootUri(serviceProperties.getDns()).build();
    }

}
