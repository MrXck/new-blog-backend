package com.blog.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    boolean exists(String filePath);

    String upload(String filePath, MultipartFile file);

    void delete(String filePath);
}
