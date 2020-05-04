package org.debugroom.sample.spring.security.common.apinfra.cloud.aws;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class S3Info {

    private String bucketName;
    private String s3DownloadAccessRole;
    private String s3UploadAccessRole;

}
