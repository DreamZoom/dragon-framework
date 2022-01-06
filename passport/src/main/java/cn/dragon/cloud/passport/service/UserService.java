package cn.dragon.cloud.passport.service;

import cn.dragon.cloud.passport.domain.UserVO;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.IDragonService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@ApiService(name = "用户服务")
public class UserService implements IDragonService {
    public String hello(String name,Integer age,UserVO user){
        return String.format("hello,%s,%d,%s",name,age,user.toString());
    }

    public String upload(MultipartFile file){
        return file.getOriginalFilename();
    }
}
