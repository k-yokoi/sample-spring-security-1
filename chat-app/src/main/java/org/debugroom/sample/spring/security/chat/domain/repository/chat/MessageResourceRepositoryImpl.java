package org.debugroom.sample.spring.security.chat.domain.repository.chat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.debugroom.sample.spring.security.common.apinfra.exception.SystemException;
import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessExceptionResponse;
import org.debugroom.sample.spring.security.common.apinfra.exception.ErrorResponse;
import org.debugroom.sample.spring.security.common.model.message.MessageResource;

@Component
public class MessageResourceRepositoryImpl implements MessageResourceRepository{

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
    public List<MessageResource> findByMessageBoardId(String messageBoardId)
            throws BusinessException {
        String endpoint = SERVICE_NAME + API_VERSION + "/messageboards/{messageBoardId}/messages";
        try{
            return Arrays.asList(restOperations.getForObject(UriComponentsBuilder
                    .fromPath(endpoint).build(messageBoardId).toString(), MessageResource[].class));
        }catch (HttpClientErrorException e){
            try{
                ErrorResponse errorResponse = objectMapper.readValue(
                        e.getResponseBodyAsString(), ErrorResponse.class);
                if(errorResponse instanceof BusinessExceptionResponse){
                    throw ((BusinessExceptionResponse)errorResponse).getBusinessException();
                }else{
                    String errorCode = "SE0001";
                    throw new SystemException(errorCode, messageSource.getMessage(
                            errorCode, new String[]{endpoint}, Locale.getDefault()), e);
                }
            }catch (IOException e1){
                String errorCode = "SE0002";
                throw new SystemException(errorCode, messageSource.getMessage(
                        errorCode, new String[]{endpoint}, Locale.getDefault()), e);
            }
        }
    }

    @Override
    public MessageResource save(MessageResource messageResource) {
        String endpoint = SERVICE_NAME + API_VERSION + "/messageboards/{messageBoardId}/messages/new";
        return restOperations.postForObject(UriComponentsBuilder
                .fromPath(endpoint).build(messageResource.getMessageBoardId()).toString(),
                messageResource, MessageResource.class);
    }

}
