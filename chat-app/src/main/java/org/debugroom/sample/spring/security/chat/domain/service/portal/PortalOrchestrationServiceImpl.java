package org.debugroom.sample.spring.security.chat.domain.service.portal;

import org.debugroom.sample.spring.security.chat.app.model.portal.PortalInformation;
import org.debugroom.sample.spring.security.chat.domain.repository.portal.PortalUserResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalOrchestrationServiceImpl implements PortalOrchestrationService {

    @Autowired
    PortalUserResourceRepository portalUserResourceRepository;

    @Override
    public PortalInformation getPortalInformation(String userId) {
        return PortalInformation.builder()
                .userResource(portalUserResourceRepository.findOne(userId))
                .build();
    }

}
