package com.example.reggie_takeout.controller;

import com.example.reggie_takeout.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {
    //存放文件基本路径
    @Value("${reggie.path}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        String originalFileName= file.getName();
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
}
