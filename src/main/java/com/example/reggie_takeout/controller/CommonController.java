package com.example.reggie_takeout.controller;

import com.example.reggie_takeout.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {
    //存放文件基本路径
    @Value("${reggie.path}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        String originalFileName= file.getOriginalFilename();
        //文件后缀名
        String suffix=originalFileName.substring(originalFileName.lastIndexOf("."));
        //使用UUID重新生成文件名
        String fileName= UUID.randomUUID().toString()+suffix;
        //创建目录对象
        File dir=new File(basePath);
        //目录不存在创建目录
        if(!dir.exists()){
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    };

    @GetMapping("/download")
    public void downLoad(String name, HttpServletResponse response){

        try {
            //创建输入流
            FileInputStream inputStream=new FileInputStream(new File(basePath+name));
            //创建输出流
            ServletOutputStream outputStream=response.getOutputStream();
            response.setContentType("image/jpeg");
            int len=0;
            byte[] bytes=new byte[1024];
            while((len= inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
