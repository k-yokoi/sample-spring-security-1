package org.debugroom.sample.spring.security.backend.message.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.Message;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MessageDTO {

    private Message message;
    private User user;

}
