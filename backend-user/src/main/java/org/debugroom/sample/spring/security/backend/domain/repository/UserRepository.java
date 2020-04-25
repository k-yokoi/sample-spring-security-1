package org.debugroom.sample.spring.security.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.debugroom.sample.spring.security.backend.domain.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.credentialsByUserId where u.userId = :userId")
    User findByUserId(@Param("userId") long userId);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.credentialsByUserId where u.loginId = :loginId")
    User findByLoginId(@Param("loginId") String loginId);

}
