package cn.dragon.framework.security;

import org.springframework.util.DigestUtils;

public class Md5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String temp = DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
        return temp.equals(encodedPassword);
    }
}
