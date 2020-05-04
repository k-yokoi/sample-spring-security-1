package org.debugroom.sample.spring.security.chat.app.model.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.debugroom.sample.spring.security.common.model.message.UserResource;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Message {

    private String messageBoardId;
    private UserResource userResource;
    private String comment;

}
