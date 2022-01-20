package cn.dragon.cloud.passport.repository;


import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.cloud.passport.domain.Permission;
import cn.dragon.cloud.passport.domain.Role;
import cn.dragon.framework.repository.BaseRepository;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface RoleRepository extends BaseRepository<Role,String> {
    List<Role> queryRoles(String accountId);
    List<Role> queryRolesByUsername(String username);
    int deleteRoles(String accountId);
    List<String> queryExistRoles(String accountId,String[] roles);
    int insertAccountRole(String accountId,String roleId);
}
