package cn.dragon.framework.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public interface IQueryExpression {
    void where(QueryWrapper query,String columnName,Object value);
}
