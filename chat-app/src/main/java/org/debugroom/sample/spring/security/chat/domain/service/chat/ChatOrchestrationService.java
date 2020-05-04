package org.debugroom.sample.spring.security.chat.domain.service.chat;

import java.util.List;

import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.debugroom.sample.spring.security.common.model.message.MessageBoardResource;
import org.debugroom.sample.spring.security.common.model.message.MessageResource;
import org.debugroom.sample.spring.security.common.model.message.UserResource;

public interface ChatOrchestrationService {

    public List<MessageBoardResource>
        getMessageBoardResources(String userId);

    public List<MessageResource> getMessageResources(
            String messageBoardId) throws BusinessException;

    public MessageResource postMessage(MessageResource messageResource);

    public List<UserResource> getUsersBelongToGroupRelatedMessageBoard(
            String messageBoardId) throws BusinessException;

    public UserResource getUserResource(String userId) throws BusinessException;

}
