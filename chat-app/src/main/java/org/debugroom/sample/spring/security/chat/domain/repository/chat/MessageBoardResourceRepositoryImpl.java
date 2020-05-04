package org.debugroom.sample.spring.security.chat.domain.repository.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.debugroom.sample.spring.security.common.model.message.MessageBoardResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class MessageBoardResourceRepositoryImpl implements MessageBoardResourceRepository{

    private static final String SERVICE_NAME = "/backend/message";
    private static final String API_VERSION = "/api/v1";

    @Autowired
    MessageSource messageSource;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    @Qualifier("messageRestOperations")
    RestOperations restOperations;

    @Override
    public List<MessageBoardResource> findByUserId(long userId) {
        String endpoint = SERVICE_NAME + API_VERSION + "/users/{userId}/messageboards";
        return Arrays.asList(restOperations.getForObject(
                UriComponentsBuilder.fromPath(endpoint).build(userId).toString(),
                MessageBoardResource[].class));
    }

}
