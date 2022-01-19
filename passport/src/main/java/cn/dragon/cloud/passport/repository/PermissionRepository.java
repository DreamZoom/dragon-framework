package cn.dragon.cloud.passport.repository;


import cn.dragon.cloud.passport.domain.Permission;
import cn.dragon.framework.repository.BaseRepository;

import java.util.List;


public interface PermissionRepository extends BaseRepository<Permission,String> {
    List<Permission> queryPermissions(String roleId);
    int deletePermissions(String roleId);
    List<String> queryExistPermissions(String roleId,String[] permissions);
    int insertRolePermission(String roleId,String permissionId);
}
