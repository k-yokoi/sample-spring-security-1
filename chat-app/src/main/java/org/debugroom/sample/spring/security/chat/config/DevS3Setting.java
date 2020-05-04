package org.debugroom.sample.spring.security.chat.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.amazonaws.services.s3.AmazonS3;
import org.debugroom.sample.spring.security.common.apinfra.cloud.aws.S3Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import org.debugroom.sample.spring.security.chat.app.web.helper.S3UploadHelper;

import java.io.IOException;
import java.io.InputStream;

@Profile("dev")
@Component
public class DevS3Setting {

    @Value("classpath:taro.png")
    Resource imageFileResource1;

    @Value("classpath:hanako.png")
    Resource imageFileResource2;

    @Value("classpath:jiro.png")
    Resource imageFileResource3;

    @Autowired
    S3UploadHelper s3UploadHelper;

    @Autowired
    AmazonS3 amazonS3;

    @Autowired
    S3Info s3Info;

    @PostConstruct
    public void initS3(){
        try(InputStream inputStream1 = imageFileResource1.getInputStream();
            InputStream inputStream2 = imageFileResource2.getInputStream();
            InputStream inputStream3 = imageFileResource3.getInputStream();){
            s3UploadHelper.save("taro.png", inputStream1);
            s3UploadHelper.save("hanako.png", inputStream2);
            s3UploadHelper.save("jiro.png", inputStream3);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void exitEvent(){
        amazonS3.deleteObject(s3Info.getBucketName(), "taro.png");
        amazonS3.deleteObject(s3Info.getBucketName(), "hanako.png");
        amazonS3.deleteObject(s3Info.getBucketName(), "jiro.png");
    }

}
