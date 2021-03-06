package cn.dragon.cloud.passport.service;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.cloud.passport.repository.AccountRepository;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.exception.ApiException;
import cn.dragon.framework.security.PasswordEncoder;
import cn.dragon.framework.service.BaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@ApiService(name = "账户服务")
public class AccountService extends BaseService<Account,String> {


    @Resource
    private AccountRepository accountRepository;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    RoleService roleService;

    @Value("${account.default-status:1}")
    Integer defaultStatus;

    @Api(name = "账户更新")
    @Transactional
    public Account save(Account model,String[] roles) throws Exception {
        //id为空表示新增，需要加密初始密码
        if(StringUtils.isEmpty(model.getId())){
            model.setStatus(defaultStatus);
            model.setPassword(passwordEncoder.encode(model.getPassword()));
        }

        Account account = super.save(model);
        if(account.getRoot()>0 && account.getStatus()<=0 ){
            throw new ApiException("超级账户状态不能禁用");
        }
        roleService.updateRoles(account.getId(),roles);
        return account;
    }

    @Api(name = "账户查询")
    @Override
    public Account find(String id) {
        return super.find(id);
    }

    @Api(name = "账户删除")
    @Override
    public void delete(String id) throws Exception {
        Account account = this.find(id);
        if(account==null) throw new ApiException("账户不存在");
        if(account.getRoot()>0) throw new ApiException("超级账户不能删除");
        super.delete(id);
    }

    @Api(name = "账户分页查询")
    @Override
    public Page<Account> queryAll(Account model, Page page) {
        Page<Account> result =super.queryAll(model, page);
        return result;
    }

    @Api(name = "账户重置密码")
    public Account resetPassword(String id,String password) throws Exception {
        //id为空表示新增，需要加密初始密码
        Account account = new Account();
        account.setId(id);
        account.setPassword(passwordEncoder.encode(password));
        return super.save(account);
    }



    ///////////////////////

    /***
     * 查找用户 根据用户名
     * @param username 用户名称
     * @return 用户实体
     */
    public Account findByUsername(String username){
        return accountRepository.findByUsername(username);
    }
}
