package com.blog.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadImage(MultipartFile multipartFile);

    String uploadPhoto(MultipartFile multipartFile);
}
