package org.debugroom.sample.spring.security.backend.message.domain.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import org.debugroom.sample.spring.security.backend.message.domain.model.entity.Group;

@EnableScan
public interface GroupRepository extends CrudRepository<Group, String> {
}
