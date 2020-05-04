package org.debugroom.sample.spring.security.chat.app.web.helper;

import org.debugroom.sample.spring.security.chat.app.web.helper.S3DirectDownloadHelper;
import org.debugroom.sample.spring.security.common.model.message.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImagePathConvertor {

    @Autowired
    S3DirectDownloadHelper s3DirectDownloadHelper;

    public void convertImagePathToPresignedUrl(UserResource userResource){
        userResource.setImageFilePath(
                s3DirectDownloadHelper.getPresignedUrl(
                        userResource.getImageFilePath()).toString());
    }

}
