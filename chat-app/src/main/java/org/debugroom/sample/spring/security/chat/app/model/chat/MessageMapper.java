package org.debugroom.sample.spring.security.chat.app.model.chat;

import org.debugroom.sample.spring.security.common.model.message.MessageResource;
import org.debugroom.sample.spring.security.common.model.message.UserResource;

public interface MessageMapper {

    public static MessageResource mapToResource(Message message){
        return MessageResource.builder()
                .messageBoardId(message.getMessageBoardId())
                .userResource(UserResource.builder()
                        .userId(Long.valueOf(message.getUserResource().getUserId()))
                        .build())
                .comment(message.getComment())
                .build();
    }

}
