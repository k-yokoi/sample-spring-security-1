package org.debugroom.sample.spring.security.backend.message.domain.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import org.debugroom.sample.spring.security.backend.message.domain.model.entity.UserGroupRelation;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.UserGroupRelationPK;

import java.util.List;

@EnableScan
public interface UserGroupRelationRepository
        extends CrudRepository<UserGroupRelation, UserGroupRelationPK> {

    List<UserGroupRelation> findByUserId(Long userId);

    List<UserGroupRelation> findByGroupId(String groupId);

}
