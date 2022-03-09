package com.example.uitils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

public class FileUtils {
    public static String upload(MultipartFile imageFolder, String path, String fileLocation) throws FileNotFoundException {
        String fileFinishName = null;

        try {
            // 如果目录不存在则创建

//                    File targetFile = new File(imageFolder,file.getOriginalFilename());

            File targetFile = new File(String.valueOf(imageFolder));
            if(!targetFile.getParentFile().exists())
            targetFile.getParentFile().mkdirs();
            File uploadDir = new File(fileLocation);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            //获取源文件名称
            String fileName = imageFolder.getOriginalFilename();
            fileFinishName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
            //上传文件到指定目录下
            File uploadFile = new File(uploadDir + uploadDir.separator + fileFinishName);
            imageFolder.transferTo(uploadFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return path + "/" + fileFinishName;
    }
}
