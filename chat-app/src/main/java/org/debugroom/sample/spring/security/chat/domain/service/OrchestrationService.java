package org.debugroom.sample.spring.security.chat.domain.service;

import org.debugroom.sample.spring.security.chat.app.model.PortalInformation;
import org.debugroom.sample.spring.security.common.model.UserResource;

public interface OrchestrationService {

    public PortalInformation getPortalInformation(String userId);

}
