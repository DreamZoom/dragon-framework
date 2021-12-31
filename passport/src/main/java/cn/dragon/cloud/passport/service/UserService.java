package cn.dragon.cloud.passport.service;

import cn.dragon.framework.IDragonService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserService implements IDragonService {
    public String hello(String name){
        return String.format("hello,%s",name);
    }
}
