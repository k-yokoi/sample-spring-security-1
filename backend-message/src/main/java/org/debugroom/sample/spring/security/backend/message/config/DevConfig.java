package org.debugroom.sample.spring.security.backend.message.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.debugroom.sample.spring.security.common.apinfra.cloud.aws.CloudFormationStackResolver;

@Profile("dev")
@Configuration
public class DevConfig {

    private static final String DYNAMODB_ENDPOINT_EXPORT = "SampleSpringSecurityDynamoDB-Dev-ServiceEndpoint";
    private static final String DYNAMODB_REGION_EXPORT = "SampleSpringSecurityDynamoDB-Dev-Region";

    @Autowired
    CloudFormationStackResolver cloudFormationStackResolver;

    @Bean
    DynamoDBMapperConfig dynamoDBMapperConfig(){
        return DynamoDBMapperConfig.builder()
                .withTableNameOverride(
                        DynamoDBMapperConfig.TableNameOverride
                        .withTableNamePrefix("dev_"))
                .build();
    }

    @Bean
    AmazonDynamoDB amazonDynamoDB(){
        return AmazonDynamoDBAsyncClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                        cloudFormationStackResolver.getExportValue(DYNAMODB_ENDPOINT_EXPORT),
                        cloudFormationStackResolver.getExportValue(DYNAMODB_REGION_EXPORT)))
                .build();
    }

}
