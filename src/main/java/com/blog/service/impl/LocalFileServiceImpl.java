package com.blog.service.impl;

import com.blog.config.LocalConfig;
import com.blog.exception.APIException;
import com.blog.service.FileService;
import com.blog.service.PhotoService;
import com.blog.utils.SpringUtils;
import com.blog.utils.UserThreadLocal;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

public class LocalFileServiceImpl implements FileService {

    private final String PATH;

    private PhotoService photoService;

    public LocalFileServiceImpl(LocalConfig localConfig) {
        PATH = localConfig.getPath();
    }

    @Override
    public boolean exists(String filePath) {
        try {
            if (Paths.get(PATH, filePath).toFile().exists()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public String upload(String filePath, MultipartFile file) {
        if (photoService == null) {
            photoService = SpringUtils.getBean(PhotoService.class);
        }

        String newPath;

        try {
            File path = Paths.get(PATH).toFile();
            if (!path.exists()) {
                try {
                    path.mkdirs();
                } catch (Exception e) {

                }
            }

            newPath = checkFile(filePath, UserThreadLocal.get().toString() + "_", file.getOriginalFilename());
            file.transferTo(Paths.get(PATH, newPath).toFile());
            photoService.add(file.getOriginalFilename(), newPath);
        } catch (IOException e) {
            throw new APIException("上传失败");
        }

        return "/file/download/" + newPath;
    }

    @Override
    public void delete(String filePath) {

    }

    private String checkFile(String filePath, String id, String filename) {
        String[] split = filename.split("\\.");
        String nowName = id + UUID.randomUUID() + "." + split[split.length - 1];

        if (exists(nowName)) {
            checkFile(filePath, id, filename);
        }

        return nowName;
    }
}
