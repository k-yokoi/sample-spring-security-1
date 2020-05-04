package org.debugroom.sample.spring.security.chat.domain.repository.chat;

import org.debugroom.sample.spring.security.common.model.message.MessageBoardResource;

import java.util.List;

public interface MessageBoardResourceRepository {

    public List<MessageBoardResource> findByUserId(long userId);

}
