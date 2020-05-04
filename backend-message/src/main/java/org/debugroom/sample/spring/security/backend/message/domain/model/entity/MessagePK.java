package org.debugroom.sample.spring.security.backend.message.domain.model.entity;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MessagePK implements Serializable {

    @DynamoDBHashKey
    private String messageBoardId;
    @DynamoDBRangeKey
    private String createdAt;

}
