package com.example.demo.Controller;



import com.example.demo.Service.UserService;
import com.example.demo.dto.FileDto;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.Date;



@Controller
public class FileUploadController {



    // 通过 Spring4 的 Value注解，获取配置文件中的属性值
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;     // ftp 服务器ip地址
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;       // ftp 服务器port，默认是21
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;    // ftp 服务器用户名
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;    // ftp 服务器密码
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;   // ftp 服务器存储图片的绝对路径
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;  // ftp 服务器外网访问图片路径
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
//    @Value("${file.location.path}")
//    private String fileLocation;


    @RequestMapping("/file/upload")
    @ResponseBody
//    MultipartFile file,
    public FileDto insert(HttpServletRequest request, @RequestParam(value = "editormd-image-file", required = false) MultipartFile file) throws Exception {
//            File    targetFile=new File("http://81.68.232.81:8080/upload/");
//            String  name=getUploadFileName(file);
//            System.out.print(name);
//            File    targetname=new File(targetFile,name);
//
//            file.transferTo(targetname);


        // 1. 取原始文件名
//        String oldName = file.getOriginalFilename();
        String oldName = getUploadFileName(file);


        // 2. ftp 服务器的文件名
        String newName = oldName;
        //图片上传
        boolean result = uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
                file.getInputStream(), FTP_BASE_PATH, newName);


        FileDto fileDto=new FileDto();
        fileDto.setSuccess(1);
        fileDto.setUrl(IMAGE_BASE_URL+ "/" + oldName);
        return fileDto;
    }

    public String getUploadFileName(MultipartFile multipartFile) {





        String uploadFileName = multipartFile.getOriginalFilename();
        String fileName = uploadFileName.substring(0,
                uploadFileName.lastIndexOf("."));
        String type = uploadFileName.substring(uploadFileName.lastIndexOf("."));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        String name = fileName + "_" + timeStr + type;
        return name;
    }




    /**
     * ftp 上传图片方法
     * @param ip            ftp 服务器ip地址
     * @param port          ftp 服务器port，默认是21
     * @param account       ftp 服务器用户名
     * @param passwd        ftp 服务器密码
     * @param inputStream   文件流
     * @param workingDir    ftp 服务器存储图片的绝对路径
     * @param fileName      上传到ftp 服务器文件名
     * @throws Exception
     *
     */
    public boolean uploadFile(String ip, Integer port, String account, String passwd,
                              InputStream inputStream, String workingDir, String fileName) throws Exception{
        boolean result = false;
        // 1. 创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();
        try {
            // 2. 创建 ftp 连接
            ftpClient.connect(ip, port);
            ftpClient.enterLocalPassiveMode();
            // 3. 登录 ftp 服务器
            ftpClient.login(account, passwd);
            int reply = ftpClient.getReplyCode(); // 获取连接ftp 状态返回值
//            System.out.println("code : " + reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect(); // 如果返回状态不再 200 ~ 300 则认为连接失败
                return result;
            }
            // 4. 读取本地文件
//          FileInputStream inputStream = new FileInputStream(new File("F:\\hello.png"));
            // 5. 设置上传的路径
            ftpClient.changeWorkingDirectory(workingDir);
            // 6. 修改上传文件的格式为二进制
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 7. 服务器存储文件，第一个参数是存储在服务器的文件名，第二个参数是文件流
//            boolean flag = ftpClient.storeFile(new String((fileName + inputStream).getBytes("UTF-8"), "iso-8859-1"), inputStream);

            if(!ftpClient.storeFile(fileName, inputStream)){
                return  result;
            }
//            ftpClient.storeFile(fileName, inputStream)
//            if (!ftpClient.storeFile(new String((fileName + inputStream).getBytes("UTF-8"), "iso-8859-1"), inputStream)) {
//                return result;
//            }
            // 8. 关闭连接
            inputStream.close();
            ftpClient.logout();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // FIXME 听说，项目里面最好少用try catch 捕获异常，这样会导致Spring的事务回滚出问题？？？难道之前写的代码都是假代码！！！
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

}

