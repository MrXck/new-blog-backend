package com.blog.config;

import lombok.Data;

@Data
public class MinioConfig {

    private String url;

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucketName;
}
