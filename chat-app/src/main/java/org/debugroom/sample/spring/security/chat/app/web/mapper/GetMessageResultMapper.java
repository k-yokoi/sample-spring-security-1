package org.debugroom.sample.spring.security.chat.app.web.mapper;

import org.debugroom.sample.spring.security.chat.app.model.chat.GetMessagesResult;
import org.debugroom.sample.spring.security.chat.app.web.helper.S3DirectDownloadHelper;
import org.debugroom.sample.spring.security.common.model.message.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GetMessageResultMapper {

    @Autowired
    S3DirectDownloadHelper s3DirectDownloadHelper;

    public GetMessagesResult map(List<MessageResource> messageResources){
        Map<String, String> presignedUrlCache = new HashMap<>();
        return GetMessagesResult.builder()
                .messageResources(messageResources.stream()
                        .map(messageResource -> {
                            String imageKey = messageResource
                                    .getUserResource().getImageFilePath();
                            String presignedImageUrl;
                            if (presignedUrlCache.containsKey(imageKey)){
                                presignedImageUrl = presignedUrlCache.get(imageKey);
                            }else {
                                presignedImageUrl = s3DirectDownloadHelper
                                        .getPresignedUrl(imageKey).toString();
                                presignedUrlCache.put(imageKey, presignedImageUrl);
                            }
                            messageResource.getUserResource()
                                    .setImageFilePath(presignedImageUrl);
                            return  messageResource;
                        })
                        .collect(Collectors.toList()))
                .build();
    }

}
