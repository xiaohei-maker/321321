package com.example.demo.Controller;



import com.example.demo.Service.UserService;
import com.example.demo.dto.FileDto;
import com.example.uitils.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Controller
public class FileUploadController {

    /**
     * 将图片上传到服务器根目录下
     *
     * @param @param  multipartFile
     * @param @param  request
     * @param @return
     * @return String
     * @throws
     */
    @Autowired
    UserService userService;
    @Value("${file.location.path}")
    private String fileLocation;
//    @RequestMapping("/insert1")
//    public String insert1(){
//        return "tupian";
//    }

    @RequestMapping("/file/upload")
    @ResponseBody
//    MultipartFile file,
    public FileDto insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取项目路径
        String realPath = request.getSession().getServletContext()
                .getRealPath("");
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        MultipartFile file=multipartHttpServletRequest.getFile("editormd-image-file");
        String uploadFileName = file.getOriginalFilename();




        InputStream inputStream = file.getInputStream();
        String contextPath = request.getContextPath();
        String path;
        if(contextPath!=""){
            //服务器根目录的路径
            path = realPath.replace(contextPath.substring(1), "");
        }
        path=realPath;

        //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";

        //获取文件名称
        String filename = getUploadFileName(file);

        //将文件上传的服务器根目录下的upload文件夹
//        File file = new File(uploadPath, filename);
//        FileUtils.copyInputStreamToFile(inputStream, file);
        //返回图片访问路径
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + "/upload/" + filename;

//        String uri = FileUtils.upload(file,"/upload",fileLocation);

        String uri = FileUtils.upload(file,"/upload",url);

        //获取源文件名称
        String fileName = file.getOriginalFilename();
        String fileFinishName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."), fileName.length());

        FileDto fileDto=new FileDto();
        fileDto.setSuccess(1);
        fileDto.setUrl(fileLocation+ "/" + fileFinishName );
//        fileDto.setUrl("/" + fileFinishName );

        return fileDto;
    }

    private String getUploadFileName(MultipartFile multipartFile) {
        String uploadFileName = multipartFile.getOriginalFilename();
        String fileName = uploadFileName.substring(0,
                uploadFileName.lastIndexOf("."));
        String type = uploadFileName.substring(uploadFileName.lastIndexOf("."));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        String name = fileName + "_" + timeStr + type;
        return name;
    }



}


