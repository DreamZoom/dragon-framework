package cn.dragon.cloud.passport.service;

import cn.dragon.cloud.passport.domain.Permission;
import cn.dragon.cloud.passport.repository.PermissionRepository;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.service.BaseService;
import cn.dragon.framework.web.HandlerRegistry;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@ApiService(name = "权限服务")
public class PermissionService extends BaseService<Permission,String> {

    @Resource
    HandlerRegistry handlerRegistry;

    @Resource
    PermissionRepository permissionRepository;

    @Api(name = "权限更新")
    @Override
    public Permission save(Permission model) throws Exception {
        return super.save(model);
    }

    @Api(name = "权限查询")
    @Override
    public Permission find(String id) {
        return super.find(id);
    }

    @Api(name = "权限删除")
    @Override
    public void delete(String id) {
        super.delete(id);
    }

    @Api(name = "权限分页查询")
    @Override
    public Page<Permission> queryAll(Permission model, Page page) {
        return super.queryAll(model, page);
    }

    @Api(name = "API列表查询")
    public Object queryApiList() {
        Object services =handlerRegistry.getData();
        return services;
    }

    @Api(name = "角色权限列表查询")
    public List<Permission> queryPermissions(String roleId) {
        return permissionRepository.queryPermissions(roleId);
    }

    @Api(name = "查询所有权限")
    public List<Permission> queryAllPermissions() {
        return this.queryAll(null);
    }

    /**
     * 更新角色的权限
     * @param roleId
     * @param permissions
     * @return
     */
    public void updatePermissions(String roleId,String[] permissions) {
        //删除不存在的数据
        permissionRepository.deletePermissionNotIn(roleId,permissions);
        //查询已存在的数据
        List<String> exists = permissionRepository.queryExistPermissions(roleId,permissions);
        List<String> permissionsList = CollectionUtils.arrayToList(permissions);
        //排除已存在的id
        permissionsList.removeAll(exists);

        for (int i = 0; i <permissionsList.size() ; i++) {
            permissionRepository.insertRolePermission(roleId,permissionsList.get(i));
        }
    }

}
