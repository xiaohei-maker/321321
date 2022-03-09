//package com.example.demo;
//
//
//
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@SpringBootTest
//class DemoApplicationTests {
//
//    @Test
//    public void testFtpClient() throws Exception {
//        // 1. 创建一个FtpClient对象
//        FTPClient ftpClient = new FTPClient();
//        // 2. 创建 ftp 连接
////        ftpClient.connect("81.68.232.81", 21);
////        // 3. 登录 ftp 服务器
////        ftpClient.login("test", "123456");
////        ftpClient.setControlEncoding("UTF-8");
//
////        ftpClient.connect("81.68.232.81", 21);
////        ftpClient.enterLocalPassiveMode();
////        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
////        ftpClient.setControlEncoding("UTF-8");
//
//        // 4. 读取本地文件
//        FileInputStream inputStream = new FileInputStream(new File("D:\\image\\1.png"));
//        // 5. 设置上传的路径
////        ftpClient.changeWorkingDirectory("/usr/local/nginx/html/images");
//        // 6. 修改上传文件的格式为二进制
////        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
////        System.out.print(        ftpClient.storeFile("2.jpg", inputStream));
//
//        // 7. 服务器存储文件，第一个参数是存储在服务器的文件名，第二个参数是文件流
////        ftpClient.storeFile("3.jpg", inputStream);
////        System.out.print(        ftpClient.storeFile("2.jpg", inputStream));
//
//        try {
//            //ftp初始化的一些参数
//            ftpClient.connect("81.68.232.81", 21);
//            ftpClient.enterLocalPassiveMode();
//            ftpClient.changeWorkingDirectory("/usr/local/nginx/html/images");
//            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.setControlEncoding("UTF-8");
//            if (ftpClient.login("test", "123456")) {
//                System.out.print(        ftpClient.storeFile("2.jpg", inputStream));
//
////                log.info("连接ftp成功");
////                flag = true;
//            } else {
////                log.error("连接ftp失败，可能用户名或密码错误");
//                try {
////                    disconnect(ftpClient);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (IOException e) {
////            log.error("连接失败，可能ip或端口错误");
//            e.printStackTrace();
//        }
//
//
//
////        if (connect(ftpClient, hostname, port, username, password)) {
////            try {
////                //2 检查工作目录是否存在
////                if (ftpClient.changeWorkingDirectory(workingPath)) {
////                    // 3 检查是否上传成功
////                    if (storeFile(ftpClient, saveName, inputStream)) {
////                        flag = true;
////                        disconnect(ftpClient);
////                    }
////                }
////            } catch (IOException e) {
////                log.error("工作目录不存在");
////                e.printStackTrace();
////                disconnect(ftpClient);
////            }
////        }
//
//
//        System.out.print(        ftpClient.storeFile("2.jpg", inputStream));
//        // 8. 关闭连接
//        ftpClient.logout();
//    }
//
//
//
//
//}
