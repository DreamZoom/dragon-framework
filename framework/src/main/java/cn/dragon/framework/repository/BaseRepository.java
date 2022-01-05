package cn.dragon.framework.repository;

import cn.dragon.framework.domain.BaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface BaseRepository<T extends BaseEntity,ID> extends BaseMapper<T> {

}
