package com.blog.service.impl;

import com.blog.config.MinioConfig;
import com.blog.exception.APIException;
import com.blog.service.FileService;
import com.blog.service.PhotoService;
import com.blog.utils.SpringUtils;
import com.blog.utils.UserThreadLocal;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

public class MinioFileServiceImpl implements FileService {

    private final MinioClient minioClient;

    private final String bucketName;

    private PhotoService photoService;

    public MinioFileServiceImpl(MinioConfig minioConfig) {
        minioClient = MinioClient.builder()
                .endpoint(minioConfig.getEndpoint())
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                .build();
        bucketName = minioConfig.getBucketName();
    }

    @Override
    public boolean exists(String filePath) {
        boolean exist = true;
        try {
            minioClient
                    .statObject(StatObjectArgs.builder().bucket(bucketName).object(filePath).build());
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }

    @Override
    public String upload(String filePath, MultipartFile file) {
        if (photoService == null) {
            photoService = SpringUtils.getBean(PhotoService.class);
        }

        String newPath;
        try {
            newPath = checkFile(filePath, UserThreadLocal.get().toString() + "_", file.getOriginalFilename());
            InputStream inputStream = file.getInputStream();

            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(newPath).stream(
                                    inputStream, inputStream.available(), -1)
                            .build());
            photoService.add(file.getOriginalFilename(), newPath);
        } catch (Exception e) {
            throw new APIException("上传失败");
        }

        return "blog/" + newPath;
    }

    @Override
    public void delete(String filePath) {

    }

    public String checkFile(String filePath, String id, String filename) {
        String[] split = filename.split("\\.");
        String nowName = id + UUID.randomUUID() + "." + split[split.length - 1];

        if (exists(filePath + nowName)) {
            checkFile(filePath, id, filename);
        }

        return filePath + nowName;
    }
}
