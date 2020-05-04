package org.debugroom.sample.spring.security.chat.app.model.portal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.debugroom.sample.spring.security.common.model.user.UserResource;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PortalInformation implements Serializable {

    private UserResource userResource;

}
