package org.debugroom.sample.spring.security.backend.message.app.web;

import java.util.List;

import org.debugroom.sample.spring.security.backend.message.app.model.UserResourceMapper;
import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.debugroom.sample.spring.security.common.model.message.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.debugroom.sample.spring.security.backend.message.app.model.MessageBoardResourceMapper;
import org.debugroom.sample.spring.security.backend.message.app.model.MessageResourceMapper;
import org.debugroom.sample.spring.security.common.model.message.MessageBoardResource;
import org.debugroom.sample.spring.security.common.model.message.MessageResource;
import org.debugroom.sample.spring.security.backend.message.domain.service.SampleService;

@RestController
@RequestMapping("api/v1")
public class BackendMessageController {

    @Autowired
    SampleService sampleService;

    @GetMapping("users/{userId}/messageboards")
    public List<MessageBoardResource> getMessageBoards(@PathVariable Long userId){
        return MessageBoardResourceMapper.map(
                sampleService.getMessageBoards(userId));
    }

    @GetMapping("messageboards/{messageBoardId}/messages")
    public List<MessageResource> getMessages(@PathVariable String messageBoardId){
        return MessageResourceMapper.map(sampleService.getMessages(messageBoardId));
    }

    @PostMapping("messageboards/{messageBoardId}/messages/new")
    public MessageResource addMessage(@RequestBody MessageResource messageResource){
        return MessageResourceMapper.map(
                sampleService.addMessage(MessageResourceMapper
                        .mapToEntity(messageResource)));
    }

    @GetMapping("messageboards/{messageBoardId}/group/users")
    public List<UserResource> getUsersBelongToGroupHavingMessageBoard(
            @PathVariable String messageBoardId) throws BusinessException {
        return UserResourceMapper.map(sampleService
                .getUsersBelongToGroupHavingMessageBoard(messageBoardId));

    }

    @GetMapping("users/{userId}")
    public UserResource getUser(@PathVariable Long userId) throws BusinessException{
        return UserResourceMapper.map(
                sampleService.getUser(userId));
    }

}
