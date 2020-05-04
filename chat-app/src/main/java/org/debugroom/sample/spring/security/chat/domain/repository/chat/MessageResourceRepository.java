package org.debugroom.sample.spring.security.chat.domain.repository.chat;

import java.util.List;

import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.debugroom.sample.spring.security.common.model.message.MessageResource;

public interface MessageResourceRepository {

    public List<MessageResource> findByMessageBoardId(
            String messageBoardId) throws BusinessException;


    public MessageResource save(MessageResource messageResource);

}
