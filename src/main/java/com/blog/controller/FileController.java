package com.blog.controller;

import com.blog.exception.APIException;
import com.blog.service.PhotoService;
import com.blog.service.UploadService;
import com.blog.utils.Constant;
import com.blog.utils.NotControllerResponseAdvice;
import com.blog.utils.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UploadService uploadService;

    @Value("${file.local.path}")
    private String PATH;

    @PostMapping(value = "/uploadImage")
    @NotControllerResponseAdvice
    public String insertImage(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        return uploadService.uploadImage(multipartFile);
    }

    @PostMapping(value = "/uploadImagePhoto")
    public Long insertImagePhoto(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        return uploadService.uploadPhoto(multipartFile);
    }

    @GetMapping("/download/{filename}")
    public void download(@PathVariable("filename") String filename, HttpServletResponse response) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(Paths.get(PATH,  filename).toFile());
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/octet-stream");
        int len;
        byte[] bytes = new byte[1024];
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }
        outputStream.close();
        fileInputStream.close();
    }
}
