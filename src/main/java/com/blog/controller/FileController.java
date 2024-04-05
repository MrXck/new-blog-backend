package com.blog.controller;

import com.blog.exception.APIException;
import com.blog.service.PhotoService;
import com.blog.utils.Constant;
import com.blog.utils.NoAuthorization;
import com.blog.utils.NotControllerResponseAdvice;
import com.blog.utils.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private PhotoService photoService;

    @PostMapping(value = "/uploadImage")
    @NotControllerResponseAdvice
    public String insertImage(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        File file = new File(Constant.PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            String[] split = multipartFile.getOriginalFilename().split("\\.");

            if (split.length < 2) {
                throw new APIException("上传的文件有误");
            }

            if (!Constant.SUFFIX_WHITE_LIST.contains(split[1])) {
                throw new APIException("上传的文件格式不支持");
            }

            String path = UserThreadLocal.get().toString() + "_" + UUID.randomUUID() + "." + split[1];
            multipartFile.transferTo(new File(Constant.PATH + path));

            photoService.add(multipartFile.getOriginalFilename(), "/file/download/" + path);

            return "/file/download/" + path;
        } catch (Exception e) {
            throw new APIException("文件上传失败");
        }
    }

    @GetMapping("/download/{filename}")
    @NoAuthorization
    public void download(@PathVariable("filename") String filename, HttpServletResponse response) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(Constant.PATH + filename));
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
    }
}
