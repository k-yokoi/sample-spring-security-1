package org.debugroom.sample.spring.security.backend.message.config;

import org.debugroom.sample.spring.security.common.apinfra.cloud.aws.CloudFormationStackResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CloudFormationStackResolver cloudFormationStackResolver(){
        return new CloudFormationStackResolver();
    }

}
