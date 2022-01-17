package cn.dragon.cloud.passport;

import cn.dragon.cloud.passport.service.AccountService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@MapperScan("cn.dragon.cloud.passport.repository")
public class PassportConfig {

    void regPermission(){

    }
}
