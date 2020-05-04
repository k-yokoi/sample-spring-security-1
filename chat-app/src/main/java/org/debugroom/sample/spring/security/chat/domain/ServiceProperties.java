package org.debugroom.sample.spring.security.chat.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@ConfigurationProperties(prefix = "service")
public class ServiceProperties {

    private String test;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class User{
        public String dns;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Message{
        private String dns;
    }

    private User user;
    private Message message;

}
