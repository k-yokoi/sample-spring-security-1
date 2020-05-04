package org.debugroom.sample.spring.security.chat.app.web.websocket;

import org.debugroom.sample.spring.security.chat.app.web.helper.ImagePathConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import org.debugroom.sample.spring.security.chat.app.model.chat.Message;
import org.debugroom.sample.spring.security.chat.app.model.chat.MessageMapper;
import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.debugroom.sample.spring.security.common.model.message.UserResource;
import org.debugroom.sample.spring.security.chat.domain.service.chat.ChatOrchestrationService;

import java.util.List;

@Controller
public class ChatMessageController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    ImagePathConvertor imagePathConvertor;

    @Autowired
    ChatOrchestrationService chatOrchestrationService;

    @MessageMapping("/messages/{messageBoardId}")
    public void addMessageAndPushToSubsribers(
            @DestinationVariable String messageBoardId,
            @RequestBody Message message){
        chatOrchestrationService.postMessage(
                MessageMapper.mapToResource(message));

        try{
            List<UserResource> userResources = chatOrchestrationService
                    .getUsersBelongToGroupRelatedMessageBoard(messageBoardId);
            message.setUserResource(chatOrchestrationService
                    .getUserResource(Long.toString(
                            message.getUserResource().getUserId())));
            imagePathConvertor.convertImagePathToPresignedUrl(message.getUserResource());
            userResources.stream().forEach(userResource -> {
                simpMessagingTemplate.convertAndSend(
                        new StringBuilder()
                                .append("/topic/user-")
                        .append(userResource.getUserId())
                        .toString(), message);
            });
        } catch (BusinessException e){
            // No operations.
        }
    }

}
