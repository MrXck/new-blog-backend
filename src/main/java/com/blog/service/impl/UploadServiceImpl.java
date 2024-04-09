package com.blog.service.impl;

import com.blog.enums.UploadPathEnum;
import com.blog.service.FileService;
import com.blog.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FileService fileService;

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        return fileService.upload(UploadPathEnum.PHOTO.getPath(), multipartFile);
    }

    @Override
    public String uploadPhoto(MultipartFile multipartFile) {
        return "";
    }
}
