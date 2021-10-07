package com.example.uitils;

import java.util.UUID;

public class uploadutils {
    /*
    UUID 生成随机的字符串
    生成唯一的文件名：
    * */
    public static String getUUIDFileName(String filename){
        //由于UUID随机生成字符串，可能有我们不需要的字符例如"-"等
        //将文件名的前面部分进行截取：xx.jpg  ----> .jpg
        int idx = filename.lastIndexOf(".");//获取" . "的位置
        String extention = filename.substring(idx);//得到文件的扩展名
        String uuidfilename = UUID.randomUUID().toString().replace("-","") + extention;//将随机的字符串中的" - " 替换为 ""空，就是去掉" - "
        return uuidfilename;
    }

}
