package com.blog.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadImage(MultipartFile multipartFile);

    Long uploadPhoto(MultipartFile multipartFile);
}
