package cn.dragon.boot.container.config;

import cn.dragon.framework.security.Md5PasswordEncoder;
import cn.dragon.framework.security.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder PasswordEncoderBean(){
        return new Md5PasswordEncoder();
    }
}
