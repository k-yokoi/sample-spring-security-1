package org.debugroom.sample.spring.security.chat.app.web.helper;

import org.debugroom.sample.spring.security.common.apinfra.cloud.aws.S3Info;
import org.debugroom.sample.spring.security.common.apinfra.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Component
public class S3DownloadHelper {

    private static final String S3_BUCKET_PREFIX = "s3://";
    private static final String DIRECTORY_DELIMITER = "/";

    @Autowired
    S3Info s3Info;

    @Autowired
    ResourceLoader resourceLoader;

    public BufferedImage getImage(String imageFilePath){
        Resource resource = resourceLoader.getResource(
                new StringBuilder()
                        .append(S3_BUCKET_PREFIX)
                        .append(s3Info.getBucketName())
                        .append(DIRECTORY_DELIMITER)
                        .append(imageFilePath)
                        .toString());
        BufferedImage image = null;
        try(InputStream inputStream = resource.getInputStream()){
            return ImageIO.read(inputStream);
        }catch (IOException e){
            throw new SystemException("SE0003", imageFilePath, e);
        }
    }



}
