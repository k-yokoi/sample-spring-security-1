package org.debugroom.sample.spring.security.common.model.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResource implements Serializable {

    private long userId;
    private String firstName;
    private String familyName;
    private String displayName;
    private String imageFilePath;

}
