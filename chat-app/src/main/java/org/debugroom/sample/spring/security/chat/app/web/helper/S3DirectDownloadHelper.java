package org.debugroom.sample.spring.security.chat.app.web.helper;

import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.actions.S3Actions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.GetRoleRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.debugroom.sample.spring.security.common.apinfra.cloud.aws.S3Info;

@Component
public class S3DirectDownloadHelper implements InitializingBean {

    private static final String RESOURCE_ARN_PREFIX = "arn:aws:s3:::";
    private static final String DIRECTORY_DELIMITER = "/";

    @Autowired
    S3Info s3Info;

    @Autowired
    AWSSecurityTokenService awsSecurityTokenService;

    @Value("${sts.min.duration.minutes}")
    private int stsMinDurationMinutes;

    @Value("${s3.download.duration.seconds}")
    private int durationSeconds;

    private String roleArn;

    public URL getPresignedUrl(String objectKey){
        AmazonS3 amazonS3 = getS3ClientWithDownloadPolicy(objectKey);
        Date expiration = Date.from(ZonedDateTime.now().plusSeconds(durationSeconds).toInstant());
        return amazonS3.generatePresignedUrl(s3Info.getBucketName(), objectKey, expiration);
    }

    private AmazonS3 getS3ClientWithDownloadPolicy(String objectKey){
        String resourceArn = new StringBuilder()
                .append(RESOURCE_ARN_PREFIX)
                .append(s3Info.getBucketName())
                .append(DIRECTORY_DELIMITER)
                .append(objectKey)
                .toString();
        Statement statement = new Statement(Statement.Effect.Allow)
                .withActions(S3Actions.GetObject)
                .withResources(new com.amazonaws.auth.policy.Resource(resourceArn));
        String iamPolicy = new Policy().withStatements(statement).toJson();
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new STSAssumeRoleSessionCredentialsProvider
                        .Builder(roleArn, "roleSessionName")
                        .withRoleSessionDurationSeconds(
                                (int) TimeUnit.MINUTES.toSeconds(stsMinDurationMinutes))
                        .withScopeDownPolicy(iamPolicy)
                        .withStsClient(awsSecurityTokenService)
                        .build())
                .build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        GetRoleRequest getRoleRequest = new GetRoleRequest()
                .withRoleName(s3Info.getS3DownloadAccessRole());
        roleArn = AmazonIdentityManagementClientBuilder.standard().build()
                .getRole(getRoleRequest).getRole().getArn();
    }

}
