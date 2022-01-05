package cn.dragon.cloud.passport.service;

import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.service.BaseService;

@ApiService
public class AccountService extends BaseService<Account,String> {

    @Api
    @Override
    public Account save(Account entity) throws Exception {
        return super.save(entity);
    }
}
