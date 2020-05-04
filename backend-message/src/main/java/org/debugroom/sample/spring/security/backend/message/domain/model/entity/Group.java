package org.debugroom.sample.spring.security.backend.message.domain.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@DynamoDBTable(tableName = "group-table")
public class Group implements Serializable {

    @Id
    @DynamoDBHashKey
    private String groupId;
    @DynamoDBAttribute
    private String groupName;
    @DynamoDBAttribute
    private Date lastUpdatedAt;

}
