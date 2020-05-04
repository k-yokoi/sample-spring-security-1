package org.debugroom.sample.spring.security.chat.domain.repository.chat;

import java.util.List;

import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.debugroom.sample.spring.security.common.model.message.UserResource;

public interface ChatUserResourceRepository {

    List<UserResource> findByMessageBoardId(
            String messageBoardId) throws BusinessException;

    UserResource findByUserId(String userId) throws BusinessException;

}
