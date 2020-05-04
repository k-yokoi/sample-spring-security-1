package org.debugroom.sample.spring.security.chat.app.web;

import org.debugroom.sample.spring.security.chat.app.model.chat.ChatPortalInformation;
import org.debugroom.sample.spring.security.chat.app.model.chat.GetMessagesResult;
import org.debugroom.sample.spring.security.chat.app.model.portal.PortalInformation;
import org.debugroom.sample.spring.security.chat.app.web.mapper.GetMessageResultMapper;
import org.debugroom.sample.spring.security.chat.app.web.security.CustomUserDetails;
import org.debugroom.sample.spring.security.chat.domain.service.chat.ChatOrchestrationService;
import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class FrontendAppChatController {

    @Autowired
    ChatOrchestrationService chatOrchestrationService;

    @Autowired
    GetMessageResultMapper getMessageResultMapper;

    @GetMapping(value = "/chat")
    public String chatPortal(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                             Model model){
        model.addAttribute("userResource", customUserDetails.getUserResource());
        model.addAttribute("chatPortalInformation",
                ChatPortalInformation.builder().messageBoardResources(
                chatOrchestrationService.getMessageBoardResources(
                        customUserDetails.getUserResource().getUserId())).build());
        return "chat";
    }

    @GetMapping(value = "/chat/messages")
    public ResponseEntity<GetMessagesResult> getMessages(
            @RequestParam String messageBoardId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    getMessageResultMapper.map(chatOrchestrationService
                    .getMessageResources(messageBoardId)));
        }catch (BusinessException e){
            return ResponseEntity.status(HttpStatus.OK).body(
                    GetMessagesResult.builder().messageResources(
                            new ArrayList<>()).build());
        }
    }


}
