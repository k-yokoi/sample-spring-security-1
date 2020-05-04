package org.debugroom.sample.spring.security.backend.message.domain.service;

import org.debugroom.sample.spring.security.backend.message.domain.model.entity.*;
import org.debugroom.sample.spring.security.backend.message.domain.repository.*;
import org.debugroom.sample.spring.security.common.apinfra.util.DateUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Profile("dev")
public class InitializingDevDBService implements InitializingBean {


    @Autowired(required = false)
    UserRepository userRepository;

    @Autowired(required = false)
    GroupRepository groupRepository;

    @Autowired(required = false)
    UserGroupRelationRepository userGroupRelationRepository;

    @Autowired(required = false)
    MessageRepository messageRepository;

    @Autowired(required = false)
    MessageBoardRepository messageBoardRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        String messageBoardId1 = "93dd9a18-9c47-4b65-b371-f4bd36bcd549";
        String messageBoardId2 = "9b9870e0-5ea3-4b61-aea1-4079cc30e4b9";
        String groupId = "509d3c66-6ab8-49a9-8cb1-f8bd637e8671";
        MessageBoardPK messageBoardPK1 = MessageBoardPK.builder()
                .messageBoardId(messageBoardId1)
                .groupId(groupId)
                .build();
        MessageBoard messageBoard1 = MessageBoard.builder()
                .messageBoardPK(messageBoardPK1)
                .messageBoardId(messageBoardId1)
                .groupId(groupId)
                .title("MessageBoard")
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        MessageBoardPK messageBoardPK2 = MessageBoardPK.builder()
                .messageBoardId(messageBoardId2)
                .groupId(groupId)
                .build();
        MessageBoard messageBoard2 = MessageBoard.builder()
                .messageBoardPK(messageBoardPK2)
                .messageBoardId(messageBoardId2)
                .groupId(groupId)
                .title("メッセージボード")
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        messageBoardRepository.save(messageBoard1);
        messageBoardRepository.save(messageBoard2);
        Message message1 = Message.builder()
                .messageBoardId(messageBoardId1)
                .createdAt(DateUtils.now())
                .userId(0)
                .comment("Add comment at " + DateUtils.now())
                .likeCount(0)
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        Message message2 = Message.builder()
                .messageBoardId(messageBoardId2)
                .createdAt(DateUtils.now())
                .userId(1)
                .comment("Add comment at " + DateUtils.now())
                .likeCount(0)
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        Message message3 = Message.builder()
                .messageBoardId(messageBoardId2)
                .createdAt(DateUtils.now())
                .userId(2)
                .comment("Add comment at " + DateUtils.now())
                .likeCount(0)
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        messageRepository.save(message1);
        messageRepository.save(message2);
        messageRepository.save(message3);
        Group group = Group.builder()
                .groupId(groupId)
                .groupName("TestGroup")
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        groupRepository.save(group);
        User user1 = User.builder()
                .userId(Long.valueOf(0))
                .familyName("mynavi")
                .firstName("taro")
                .displayName("Taro")
                .imageFilePath("taro.png")
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        User user2 = User.builder()
                .userId(Long.valueOf(1))
                .familyName("mynavi")
                .firstName("hanako")
                .displayName("花子")
                .imageFilePath("hanako.png")
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        User user3 = User.builder()
                .userId(Long.valueOf(2))
                .familyName("mynavi")
                .firstName("jiro")
                .displayName("次郎丸")
                .imageFilePath("jiro.png")
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        UserGroupRelation userGroupRelation1 = UserGroupRelation.builder()
                .userId(0)
                .groupId(groupId)
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        UserGroupRelation userGroupRelation2 = UserGroupRelation.builder()
                .userId(1)
                .groupId(groupId)
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        UserGroupRelation userGroupRelation3 = UserGroupRelation.builder()
                .userId(2)
                .groupId(groupId)
                .lastUpdatedAt(DateUtils.nowDate())
                .build();
        userGroupRelationRepository.save(userGroupRelation1);
        userGroupRelationRepository.save(userGroupRelation2);
        userGroupRelationRepository.save(userGroupRelation3);
    }

}
