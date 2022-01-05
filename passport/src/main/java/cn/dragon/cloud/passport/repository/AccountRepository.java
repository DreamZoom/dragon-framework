package cn.dragon.cloud.passport.repository;


import cn.dragon.cloud.passport.domain.Account;
import cn.dragon.framework.repository.BaseRepository;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface AccountRepository extends BaseRepository<Account,String> {

    @Select("select * from account where username = #{username}")
    Account findByUsername(String username);

    @Select("select * from account where email = #{email}")
    Account findByEmail(String email);

    @Select("select * from account where tell = #{tell} limit 1")
    Account findByTell(String tell);



    @Select("select * from account where email = #{email} or username = #{username}")
    Account findByEmailOrUsername(String email, String username);




}
