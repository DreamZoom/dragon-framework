package cn.dragon.framework.query.expression;

import cn.dragon.framework.query.IQueryExpression;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class GreaterThanExpression implements IQueryExpression {
    @Override
    public void where(QueryWrapper query,String columnName,Object value) {
        query.gt(columnName,value);
    }
}
