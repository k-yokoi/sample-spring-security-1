package org.debugroom.sample.spring.security.chat.app.web.helper;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.util.IOUtils;
import org.debugroom.sample.spring.security.common.apinfra.cloud.aws.S3Info;
import org.debugroom.sample.spring.security.common.apinfra.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.core.io.s3.SimpleStorageProtocolResolver;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class S3UploadHelper {

    private static final String S3_BUCKET_PREFIX = "s3://";
    private static final String DIRECTORY_DELIMITER = "/";

    @Autowired
    S3Info s3Info;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    AmazonS3 amazonS3;

    public void save(String fileName, InputStream inputStream) throws IOException{
        String objectKey = new StringBuilder()
                .append(S3_BUCKET_PREFIX)
                .append(s3Info.getBucketName())
                .append(DIRECTORY_DELIMITER)
                .append(fileName)
                .toString();
        Resource newResource = resourceLoader.getResource(objectKey);
        if(!newResource.getClass().getName().endsWith("SimpleStorageResource")
                && resourceLoader instanceof DefaultResourceLoader){
            SimpleStorageProtocolResolver simpleStorageProtocolResolver = new SimpleStorageProtocolResolver(amazonS3);
            simpleStorageProtocolResolver.setTaskExecutor(new SyncTaskExecutor());
            newResource = simpleStorageProtocolResolver.resolve(objectKey, resourceLoader);
        }
        WritableResource writableResource = (WritableResource)newResource;
        try(OutputStream outputStream = writableResource.getOutputStream()){
            IOUtils.copy(inputStream, outputStream);
        }catch (IOException e){
            new SystemException("SE0003", objectKey, e);
        }
    }


}
