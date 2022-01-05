package cn.dragon.framework.query.expression;

import cn.dragon.framework.query.IQueryExpression;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class LessThanAndEqualExpression implements IQueryExpression {
    @Override
    public void where(QueryWrapper query,String columnName,Object value) {
        query.le(columnName,value);
    }
}
