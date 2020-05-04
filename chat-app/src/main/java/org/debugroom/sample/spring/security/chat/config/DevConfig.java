package org.debugroom.sample.spring.security.chat.config;

import org.debugroom.sample.spring.security.chat.domain.ServiceProperties;
import org.debugroom.sample.spring.security.common.apinfra.cloud.aws.CloudFormationStackResolver;
import org.debugroom.sample.spring.security.common.apinfra.cloud.aws.S3Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.aws.context.config.annotation.EnableStackConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestOperations;

@Profile("dev")
@EnableStackConfiguration(stackName = "sample-spring-security-s3")
@Configuration
public class DevConfig {

    private static final String S3_BUCKET_EXPORT = "sample-spring-security-s3-bucket-dev";
    private static final String S3_DOWNLOAD_ACCESS_ROLE_EXPORT = "sample-spring-security-s3-download-access-role";
    private static final String S3_UPLOAD_ACCESS_ROLE_EXPORT = "sample-spring-security-s3-upload-access-role";

    @Autowired
    ServiceProperties serviceProperties;

    @Bean
    CloudFormationStackResolver cloudFormationStackResolver(){
        return new CloudFormationStackResolver();
    }

    @Bean
    S3Info s3Info(){
        return S3Info.builder()
                .bucketName(cloudFormationStackResolver()
                        .getExportValue(S3_BUCKET_EXPORT))
                .s3DownloadAccessRole(cloudFormationStackResolver()
                        .getExportValue(S3_DOWNLOAD_ACCESS_ROLE_EXPORT))
                .s3UploadAccessRole(cloudFormationStackResolver()
                        .getExportValue(S3_UPLOAD_ACCESS_ROLE_EXPORT))
                .build();
    }

    @Bean
    public RestOperations userRestOperations(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.rootUri(serviceProperties.getUser().getDns()).build();
    }

    @Bean
    public RestOperations messageRestOperations(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.rootUri(serviceProperties.getMessage().getDns()).build();
    }
}
