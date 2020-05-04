package org.debugroom.sample.spring.security.backend.message.domain.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import org.springframework.data.annotation.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@DynamoDBTable(tableName = "message-table")
public class Message implements Serializable {

    @Id
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private MessagePK messagePK;
    private String messageBoardId;
    private String createdAt;
    @DynamoDBAttribute
    private String comment;
    @DynamoDBAttribute
    private long userId;
    @DynamoDBAttribute
    private String imagePath;
    @DynamoDBAttribute
    private String videoPath;
    @DynamoDBAttribute
    private int likeCount;
    @DynamoDBAttribute
    private Date lastUpdatedAt;

    @DynamoDBHashKey
    public String getMessageBoardId() {
        return messageBoardId;
    }

    @DynamoDBRangeKey
    public String getCreatedAt() {
        return createdAt;
    }

}
