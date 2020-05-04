package org.debugroom.sample.spring.security.chat.app.model.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.debugroom.sample.spring.security.common.model.message.MessageResource;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMessagesResult {

    List<MessageResource> messageResources;

}
