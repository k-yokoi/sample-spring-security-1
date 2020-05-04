package org.debugroom.sample.spring.security.backend.message.app.model;

import org.debugroom.sample.spring.security.backend.message.domain.model.entity.MessageBoard;
import org.debugroom.sample.spring.security.common.model.message.MessageBoardResource;

import java.util.List;
import java.util.stream.Collectors;

public interface MessageBoardResourceMapper {

    public static MessageBoardResource map(MessageBoard messageBoard){
        return MessageBoardResource.builder()
                .messageBoardId(messageBoard.getMessageBoardId())
                .groupId(messageBoard.getGroupId())
                .title(messageBoard.getTitle())
                .build();
    }

    public static List<MessageBoardResource> map(List<MessageBoard> messageBoards){
        return messageBoards.stream().map(
                MessageBoardResourceMapper::map).collect(Collectors.toList());
    }

}
