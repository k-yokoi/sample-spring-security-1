package org.debugroom.sample.spring.security.backend.message.domain.service;

import org.debugroom.sample.spring.security.backend.message.domain.model.dto.MessageDTO;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.Message;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.MessageBoard;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.User;
import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;

import java.util.List;

public interface SampleService {

    public List<MessageDTO> getMessages(String messageBoardId);
    public MessageDTO addMessage(Message message);
    public List<MessageBoard> getMessageBoards(Long userId);
    public List<User> getUsersBelongToGroupHavingMessageBoard(String messageBoardId) throws BusinessException;
    public User getUser(Long userId) throws BusinessException;

}
