package org.debugroom.sample.spring.security.chat.domain.service.chat;

import java.util.List;

import org.debugroom.sample.spring.security.chat.domain.repository.chat.ChatUserResourceRepository;
import org.debugroom.sample.spring.security.common.model.message.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.debugroom.sample.spring.security.chat.domain.repository.chat.MessageBoardResourceRepository;
import org.debugroom.sample.spring.security.chat.domain.repository.chat.MessageResourceRepository;
import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.debugroom.sample.spring.security.common.model.message.MessageBoardResource;
import org.debugroom.sample.spring.security.common.model.message.MessageResource;

@Service
public class ChatOrchestrationServiceImpl implements ChatOrchestrationService {

    @Autowired
    MessageBoardResourceRepository messageBoardResourceRepository;

    @Autowired
    MessageResourceRepository messageResourceRepository;

    @Autowired
    ChatUserResourceRepository chatUserResourceRepository;

    @Override
    public List<MessageBoardResource> getMessageBoardResources(String userId) {
        return messageBoardResourceRepository.findByUserId(
                Long.valueOf(userId));
    }

    @Override
    public List<MessageResource> getMessageResources(
            String messageBoardId) throws BusinessException {
        return messageResourceRepository.findByMessageBoardId(messageBoardId);
    }

    @Override
    public MessageResource postMessage(MessageResource messageResource) {
        return messageResourceRepository.save(messageResource);
    }

    @Override
    public List<UserResource> getUsersBelongToGroupRelatedMessageBoard(
            String messageBoardId) throws BusinessException{
        return chatUserResourceRepository.findByMessageBoardId(messageBoardId);
    }

    @Override
    public UserResource getUserResource(String userId) throws BusinessException{
        return chatUserResourceRepository.findByUserId(userId);
    }

}
