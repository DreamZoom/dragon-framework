package cn.dragon.cloud.passport.service;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.exception.ApiException;
import cn.dragon.framework.service.BaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;

@ApiService(name = "账户服务")
public class AccountService extends BaseService<Account,String> {



    @Api(name = "账户更新")
    @Override
    public Account save(Account model) throws Exception {
        return super.save(model);
    }

    @Api(name = "账户查询")
    @Override
    public Account find(String id) {
        return super.find(id);
    }

    @Api(name = "账户删除")
    @Override
    public void delete(String id) {
        super.delete(id);
    }

    @Api(name = "账户分页查询")
    @Override
    public Page<Account> queryAll(Account model, Page page) {
        return super.queryAll(model, page);
    }

    @Api(name = "账户注册")
    public Account register(String username,String password) throws Exception {
        if(StringUtils.isEmpty(username)){
            throw new ApiException("用户名不能为空");
        }

        if(StringUtils.isEmpty(password)){
            throw new ApiException("密码不能为空");
        }

        Account exits = this.findByFieldValue("username",username);
        if(exits!=null){
            throw new ApiException("账户已存在");
        }

        Account account =new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setStatus(1);//通过审核
        return this.save(account);
    }
}
