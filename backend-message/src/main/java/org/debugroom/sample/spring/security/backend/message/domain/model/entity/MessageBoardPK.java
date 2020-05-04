package org.debugroom.sample.spring.security.backend.message.domain.model.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MessageBoardPK implements Serializable {

    @DynamoDBHashKey
    private String messageBoardId;
    @DynamoDBRangeKey
    private String groupId;

}
