package cn.dragon.cloud.passport.service;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.cloud.passport.security.JwtUserDetails;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.IDragonService;
import cn.dragon.framework.exception.ApiException;
import cn.dragon.framework.security.*;
import org.springframework.util.StringUtils;


import javax.annotation.Resource;


@ApiService(name = "用户服务")
public class UserService implements IDragonService {

    @Resource
    TokenService tokenService;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    AccountService accountService;

    @Api(name = "账户注册")
    public Account register(String username, String password) throws Exception {
        if(StringUtils.isEmpty(username)){
            throw new ApiException("用户名不能为空");
        }

        if(StringUtils.isEmpty(password)){
            throw new ApiException("密码不能为空");
        }

        Account exits = accountService.findByFieldValue("username",username);
        if(exits!=null){
            throw new ApiException("账户已存在");
        }

        Account account =new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setStatus(1);//通过审核
        return accountService.save(account);
    }

    @Api(name = "用户登录")
    public Token login(String username,String password) throws ApiException {
        Account account = accountService.findByUsername(username);
        if(!passwordEncoder.matches(password,account.getPassword())){
            throw new ApiException("密码不正确");
        }
        UserDetails userDetails =new JwtUserDetails(account);
        return tokenService.generate(userDetails);
    }

    @Api(name = "用户信息")
    public UserDetails details(Token token) throws Exception {
        return tokenService.loadUserDetails(token);
    }

}
