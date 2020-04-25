package org.debugroom.sample.spring.security.chat.domain.repository;

import java.util.List;

import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.debugroom.sample.spring.security.common.model.UserResource;

public interface UserResourceRepository {

   public UserResource findOne(String userId);
   public UserResource findOneByLoginId(String loginId) throws BusinessException;
   public List<UserResource> findAll();

}
