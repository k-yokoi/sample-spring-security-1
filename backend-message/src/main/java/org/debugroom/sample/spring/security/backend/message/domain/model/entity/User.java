package org.debugroom.sample.spring.security.backend.message.domain.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@DynamoDBTable(tableName = "user-table")
public class User implements Serializable {

    @Id
    @DynamoDBHashKey
    private Long userId;
    @DynamoDBAttribute
    private String firstName;
    @DynamoDBAttribute
    private String familyName;
    @DynamoDBAttribute
    private String displayName;
    @DynamoDBAttribute
    private String imageFilePath;
    @DynamoDBAttribute
    private Date lastUpdatedAt;

}
