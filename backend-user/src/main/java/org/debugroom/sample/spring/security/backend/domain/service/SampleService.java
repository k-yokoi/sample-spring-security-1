package org.debugroom.sample.spring.security.backend.domain.service;

import java.util.List;

import org.debugroom.sample.spring.security.backend.domain.model.entity.User;
import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;

public interface SampleService {

    public List<User> getUsers();
    public User getUser(Long id) throws BusinessException;
    public User getUserByLoginId(String loginId) throws BusinessException;

}
