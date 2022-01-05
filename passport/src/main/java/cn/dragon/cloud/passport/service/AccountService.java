package cn.dragon.cloud.passport.service;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.service.BaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@ApiService
public class AccountService extends BaseService<Account,String> {

    @Api
    @Override
    public Account save(Account entity) throws Exception {
        return super.save(entity);
    }

    @Api
    @Override
    public Account find(String id) {
        return super.find(id);
    }

    @Api
    @Override
    public void delete(String id) {
        super.delete(id);
    }


    @Api
    @Override
    public Page<Account> queryAll(Account model, Page page) {
        return super.queryAll(model, page);
    }
}
