package org.debugroom.sample.spring.security.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("messages/{messageBoardId}")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(
                new StringBuilder().append("/chat").toString());
        registry.enableSimpleBroker("/topic", "/queue", "/notification");
    }

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry message) {
        message
                .nullDestMatcher().permitAll()
                .simpDestMatchers("/chat/**")
                .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .simpSubscribeDestMatchers("/topic/" + "**").permitAll()
                .anyMessage().denyAll();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
