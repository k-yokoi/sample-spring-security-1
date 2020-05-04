package org.debugroom.sample.spring.security.backend.message.domain.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import org.debugroom.sample.spring.security.backend.message.domain.model.entity.Message;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.MessagePK;

import java.util.List;

@EnableScan
public interface MessageRepository extends CrudRepository<Message, MessagePK> {

    List<Message> findByMessageBoardId(String messageBoardId);

}
