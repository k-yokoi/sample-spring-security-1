package org.debugroom.sample.spring.security.chat.domain.service;

import org.debugroom.sample.spring.security.chat.app.model.PortalInformation;
import org.debugroom.sample.spring.security.chat.domain.repository.UserResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrchestrationServiceImpl implements OrchestrationService{

    @Autowired
    UserResourceRepository userResourceRepository;

    @Override
    public PortalInformation getPortalInformation(String userId) {
        return PortalInformation.builder()
                .userResource(userResourceRepository.findOne(userId))
                .build();
    }

}
