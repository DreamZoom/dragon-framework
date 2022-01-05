package cn.dragon.framework.query;


import cn.dragon.framework.query.expression.*;

import java.util.HashMap;
import java.util.Map;

public class QueryExpressionFactory {
    static Map<WhereOperator, IQueryExpression> expressions = new HashMap<>();
    static {
        expressions.put(WhereOperator.LIKE, new LikeExpression());
        expressions.put(WhereOperator.LIKE_START, new LikeStartExpression());
        expressions.put(WhereOperator.LIKE_END, new LikeEndExpression());
        expressions.put(WhereOperator.IN, new InExpression());
        expressions.put(WhereOperator.EQ, new EqualExpression());
        expressions.put(WhereOperator.GT, new GreaterThanExpression());
        expressions.put(WhereOperator.GT_EQ, new GreaterThanAndEqualExpression());
        expressions.put(WhereOperator.LT, new LessThanExpression());
        expressions.put(WhereOperator.LT_EQ, new LessThanAndEqualExpression());
    }

    public static IQueryExpression create(WhereOperator operator){
        if(operator!=null && expressions.containsKey(operator)){
            return expressions.get(operator);
        }
        return null;
    }
}
