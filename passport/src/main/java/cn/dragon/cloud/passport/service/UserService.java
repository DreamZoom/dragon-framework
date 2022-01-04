package cn.dragon.cloud.passport.service;

import cn.dragon.cloud.passport.domain.UserVO;
import cn.dragon.framework.IDragonService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserService implements IDragonService {
    public String hello(UserVO user){
        return String.format("hello,%s",user.getName());
    }
}
