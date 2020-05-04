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
public class MessageResource implements Serializable {

    private String messageBoardId;
    private String createdAt;
    private String comment;
    private String imagePath;
    private String videoPath;
    private int likeCount;
    private UserResource userResource;

}
