package com.blog.service.impl;

import com.blog.enums.UploadPathEnum;
import com.blog.service.FileService;
import com.blog.service.PhotoService;
import com.blog.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FileService fileService;

    @Autowired
    private PhotoService photoService;

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        String string = fileService.upload(UploadPathEnum.PHOTO.getPath(), multipartFile);
        photoService.add(multipartFile.getOriginalFilename(), string);
        return string;
    }

    @Override
    public Long uploadPhoto(MultipartFile multipartFile) {
        String string = fileService.upload(UploadPathEnum.PHOTO_ALBUM_COVER.getPath(), multipartFile);
        return photoService.add(multipartFile.getOriginalFilename(), string);
    }
}
