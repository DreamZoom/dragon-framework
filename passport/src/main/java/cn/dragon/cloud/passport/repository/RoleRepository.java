package cn.dragon.cloud.passport.repository;


import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.cloud.passport.domain.Role;
import cn.dragon.framework.repository.BaseRepository;
import org.apache.ibatis.annotations.Select;


public interface RoleRepository extends BaseRepository<Role,String> {

}
