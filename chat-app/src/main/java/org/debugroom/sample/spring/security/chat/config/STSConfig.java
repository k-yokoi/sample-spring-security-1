package org.debugroom.sample.spring.security.chat.config;

import static org.springframework.cloud.aws.core.config.AmazonWebserviceClientConfigurationUtils.CREDENTIALS_PROVIDER_BEAN_NAME;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class STSConfig {
    
    @Autowired
    @Qualifier(CREDENTIALS_PROVIDER_BEAN_NAME)
    AWSCredentialsProvider awsCredentialsProvider;

    @Bean
    public AWSSecurityTokenService awsSecurityTokenService() {
        return AWSSecurityTokenServiceClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .build();
    }

}
