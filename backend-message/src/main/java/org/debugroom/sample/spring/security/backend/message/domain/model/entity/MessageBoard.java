package org.debugroom.sample.spring.security.backend.message.domain.model.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@DynamoDBTable(tableName = "message-board-table")
public class MessageBoard implements Serializable {

    @Id
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private MessageBoardPK messageBoardPK;
    @DynamoDBHashKey
    private String messageBoardId;
    @DynamoDBAttribute
    private String groupId;
    @DynamoDBAttribute
    private String title;
    @DynamoDBAttribute
    private Date lastUpdatedAt;

}
