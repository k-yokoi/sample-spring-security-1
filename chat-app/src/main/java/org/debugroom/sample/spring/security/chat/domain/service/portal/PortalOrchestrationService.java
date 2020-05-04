package org.debugroom.sample.spring.security.chat.domain.service.portal;

import org.debugroom.sample.spring.security.chat.app.model.portal.PortalInformation;

public interface PortalOrchestrationService {

    public PortalInformation getPortalInformation(String userId);

}
