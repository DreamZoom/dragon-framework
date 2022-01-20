package cn.dragon.cloud.passport.service;


import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.cloud.passport.domain.Permission;
import cn.dragon.cloud.passport.domain.Role;

import cn.dragon.cloud.passport.repository.RoleRepository;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.service.BaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


@ApiService(name = "角色服务")
public class RoleService extends BaseService<Role,String> {

    @Resource
    RoleRepository roleRepository;

    @Resource
    PermissionService permissionService;

    @Api(name = "角色更新")
    @Transactional
    public Role save(Role model,String[] permissions) throws Exception {
        Role role = super.save(model);
        permissionService.updatePermissions(role.getId(),permissions);
        return role;
    }

    @Api(name = "角色查询")
    @Override
    public Role find(String id) {
        return super.find(id);
    }

    @Api(name = "角色删除")
    @Override
    public void delete(String id) throws Exception {
        super.delete(id);
    }

    @Api(name = "角色分页查询")
    @Override
    public Page<Role> queryAll(Role model, Page page) {
        return super.queryAll(model, page);
    }

    @Api(name = "账户角色查询")
    public List<Role> queryRoles(String accountId) {
        return roleRepository.queryRoles(accountId);
    }

    @Api(name = "查询所有角色")
    public List<Role> queryAllRoles() {
        return this.queryAll(null);
    }


    /**
     * 更新账户角色
     * @param accountId
     * @param roles
     */
    @Transactional
    public void updateRoles(String accountId,String[] roles) {
        roleRepository.deleteRoles(accountId);
        List<String> roleList = CollectionUtils.arrayToList(roles);
        for (int i = 0; i <roleList.size() ; i++) {
            roleRepository.insertAccountRole(accountId,roleList.get(i));
        }
    }

}
