package org.debugroom.sample.spring.security.backend.message.domain.service;

import java.util.*;

import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import org.debugroom.sample.spring.security.backend.message.domain.model.dto.MessageDTO;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.Message;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.MessageBoard;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.User;
import org.debugroom.sample.spring.security.backend.message.domain.model.entity.UserGroupRelation;
import org.debugroom.sample.spring.security.backend.message.domain.repository.MessageBoardRepository;
import org.debugroom.sample.spring.security.backend.message.domain.repository.MessageRepository;
import org.debugroom.sample.spring.security.backend.message.domain.repository.UserGroupRelationRepository;
import org.debugroom.sample.spring.security.backend.message.domain.repository.UserRepository;
import org.debugroom.sample.spring.security.common.apinfra.util.DateUtils;

@Service
public class SampleServiceImpl implements SampleService{

    @Autowired
    MessageSource messageSource;

    @Autowired(required = false)
    MessageRepository messageRepository;

    @Autowired(required = false)
    UserGroupRelationRepository userGroupRelationRepository;

    @Autowired(required = false)
    MessageBoardRepository messageBoardRepository;

    @Autowired(required = false)
    UserRepository userRepository;

    @Override
    public List<MessageDTO> getMessages(String messageBoardId) {
        List<Message> messages = messageRepository
                .findByMessageBoardId(messageBoardId);
        Map<Long, Optional<User>> userCache = new HashMap<>();
        List<MessageDTO> messageDTOList = new ArrayList<>();
        messages.stream().forEach(message -> {
            Optional<User> optionalUser;
            if(userCache.containsKey(message.getUserId())){
                optionalUser = userCache.get(message.getUserId());
            }else {
                optionalUser = userRepository.findById(message.getUserId());
                if(optionalUser.isPresent()){
                    userCache.put(message.getUserId(), optionalUser);
                }else {
                    Optional<User> anonymous = Optional.of(
                            User.builder().displayName("Anonymous").build());
                    userCache.put(message. getUserId(), anonymous);
                }
            }
            messageDTOList.add(MessageDTO.builder()
            .message(message).user(optionalUser.get()).build());
        });
        return messageDTOList;
    }

    @Override
    public MessageDTO addMessage(Message message) {
        message.setMessageBoardId(message.getMessageBoardId());
        message.setCreatedAt(DateUtils.now());
        message.setComment(message.getComment());
        message.setUserId(message.getUserId());
        message.setImagePath(message.getImagePath());
        message.setVideoPath(message.getVideoPath());
        message.setLastUpdatedAt(DateUtils.nowDate());
        messageRepository.save(message);
        return MessageDTO.builder().message(message)
                .user(userRepository.findById(message.getUserId()).get())
                .build();
    }

    @Override
    public List<MessageBoard> getMessageBoards(Long userId) {
        List<UserGroupRelation> userGroupRelations =
                userGroupRelationRepository.findByUserId(userId);
        List<MessageBoard> messageBoards = new ArrayList<>();
        userGroupRelations.stream().forEach(userGroupRelation ->{
            messageBoards.addAll(messageBoardRepository.findByGroupId(
                    userGroupRelation.getGroupId()
            ));
        });
        return messageBoards;
    }

    @Override
    public List<User> getUsersBelongToGroupHavingMessageBoard(
            String messageBoardId) throws BusinessException {
        Optional<MessageBoard> optionalMessageBoard =
                messageBoardRepository.findById(messageBoardId);
        List<User> users = new ArrayList<>();
        if(optionalMessageBoard.isPresent()){
            List<UserGroupRelation> userGroupRelations =
                    userGroupRelationRepository.findByGroupId(
                            optionalMessageBoard.get().getGroupId());
            // TODO : Use Map instead of many queries.
            userGroupRelations.stream().forEach(userGroupRelation -> {
                users.add(userRepository.findById(userGroupRelation.getUserId()).get());
            });
        }else throw new BusinessException("BE0001",messageBoardId);
        return users;
    }

    @Override
    public User getUser(Long userId) throws BusinessException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else throw new BusinessException("BE0002", userId.toString());
    }

}