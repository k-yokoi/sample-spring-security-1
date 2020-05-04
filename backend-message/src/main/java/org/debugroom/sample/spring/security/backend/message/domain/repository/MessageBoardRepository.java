package org.debugroom.sample.spring.security.backend.message.domain.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.debugroom.sample.spring.security.backend.message.domain.model.entity.MessageBoard;

import java.util.List;

@EnableScan
public interface MessageBoardRepository extends CrudRepository<MessageBoard, String> {

    List<MessageBoard> findByGroupId(String groupId);

}
