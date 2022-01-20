package cn.dragon.cloud.passport.service;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.cloud.passport.security.JwtUserDetails;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.IDragonService;
import cn.dragon.framework.exception.ApiException;
import cn.dragon.framework.security.*;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${account.default-status:1}")
    Integer defaultStatus;

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
        account.setStatus(defaultStatus);//通过审核
        return accountService.save(account);
    }

    @Api(name = "用户登录",isPublic = true)
    public Token login(String username,String password) throws ApiException {
        Account account = accountService.findByUsername(username);
        if(!passwordEncoder.matches(password,account.getPassword())){
            throw new ApiException("密码不正确");
        }

        if(account.getStatus() <=0 ){
            throw new ApiException("账户未启用");
        }
        UserDetails userDetails =new JwtUserDetails(account);
        return tokenService.generate(userDetails);
    }

    @Api(name = "用户信息")
    public UserDetails details(Token token) throws Exception {
        return token.getDetails();
    }

    @Api(name = "用户信息")
    public UserDetails changePassword(Token token,String password,String new_password) throws Exception {
        String username = token.getDetails().getUsername();
        Account account = accountService.findByUsername(username);
        if(StringUtils.isEmpty(password)){
            throw new ApiException("密码不能为空");
        }

        if(StringUtils.isEmpty(new_password)){
            throw new ApiException("新密码不能为空");
        }

        if(!passwordEncoder.matches(password,account.getPassword())){
            throw new ApiException("密码不正确");
        }

        account.setPassword(passwordEncoder.encode(new_password));
        account = accountService.save(account);
        return new JwtUserDetails(account);
    }

}
