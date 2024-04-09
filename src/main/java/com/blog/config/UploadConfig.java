package com.blog.config;

import com.blog.service.FileService;
import com.blog.service.impl.LocalFileServiceImpl;
import com.blog.service.impl.MinioFileServiceImpl;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class UploadConfig {

    private String mode;

    private MinioConfig minio;

    private LocalConfig local;

    @Bean
    public FileService getFileService() {
        if ("local".equals(mode)) {
            return new LocalFileServiceImpl(local);
        } else {
            return new MinioFileServiceImpl(minio);
        }
    }
}
