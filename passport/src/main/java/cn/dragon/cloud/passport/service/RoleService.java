package cn.dragon.cloud.passport.service;


import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.cloud.passport.domain.Role;

import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.service.BaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;


@ApiService(name = "角色服务")
public class RoleService extends BaseService<Role,String> {

    @Api(name = "角色更新")
    @Override
    public Role save(Role model) throws Exception {
        return super.save(model);
    }

    @Api(name = "角色查询")
    @Override
    public Role find(String id) {
        return super.find(id);
    }

    @Api(name = "角色删除")
    @Override
    public void delete(String id) {
        super.delete(id);
    }

    @Api(name = "角色分页查询")
    @Override
    public Page<Role> queryAll(Role model, Page page) {
        return super.queryAll(model, page);
    }

}
