package org.debugroom.sample.spring.security.backend.message.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(
        basePackages = "org.debugroom.sample.spring.security.backend.message.domain.repository"
)
public class DynamoDBConfig {
}
