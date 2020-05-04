package org.debugroom.sample.spring.security.backend.message.app.model;

import org.debugroom.sample.spring.security.backend.message.domain.model.entity.User;
import org.debugroom.sample.spring.security.common.model.message.UserResource;

import java.util.List;
import java.util.stream.Collectors;

public interface UserResourceMapper {

    public static UserResource map(User user){
        return UserResource.builder()
                .userId(user.getUserId())
                .familyName(user.getFamilyName())
                .firstName(user.getFirstName())
                .displayName(user.getDisplayName())
                .imageFilePath(user.getImageFilePath())
                .build();
    }

    public static List<UserResource> map(List<User> users){
        return users.stream().map(UserResourceMapper::map)
                .collect(Collectors.toList());
    }

}
