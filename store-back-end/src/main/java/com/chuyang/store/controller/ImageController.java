package com.chuyang.store.controller;

import com.chuyang.store.service.GoodsService;
import com.chuyang.store.service.UserService;
import com.chuyang.store.utils.APIResult;
import com.chuyang.store.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @ClassName ImageController
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/7/5 14:57
 * @Version 1.0
 **/
@RestController
@RequestMapping("/images")
public class ImageController {
    @Value("${web.upload-path}")
    private String path;
    @Autowired
    private UserService userService;

    @RequestMapping("/avatarUpload")
    public APIResult uploadAvatarImage(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") Integer id){
        String destinationPath=path;
        String newFileName= FileUtil.upload(destinationPath,multipartFile);
        newFileName=newFileName.replace("C:","http://localhost:8080/store-back-end");
        APIResult apiResult=userService.updateAvatarById(id,newFileName);
        apiResult.setData(newFileName);
        return apiResult;
    }

    @RequestMapping("/uploadImage")
    public APIResult uploadImage(@RequestParam("file") MultipartFile multipartFile){
        System.out.println(multipartFile);
        String destinationPath=path;
        String newFileName= FileUtil.upload(destinationPath,multipartFile);
        try{
            newFileName=newFileName.replace("C:","http://localhost:8080/store-back-end");
            return APIResult.createOk(newFileName);
        }catch (Exception e){
            e.printStackTrace();
            return APIResult.createNg("图片上传失败");
        }
    }
}
