package cn.dragon.framework.query;

import cn.dragon.framework.utils.CaseUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryBuilder {
    protected static Logger logger = LoggerFactory.getLogger(QueryBuilder.class);
    public static <T> QueryWrapper<T> build(Object model){
        QueryWrapper<T> query = Wrappers.query();
        return build(query,model);
    }
    public static <T> QueryWrapper<T> build(QueryWrapper<T> query,Object model){
        query = where(query,model);
        return sort(query,model);
    }
    public static <T> QueryWrapper<T> where(QueryWrapper<T> query, Object model){
        if(model==null) return null;

        Field[] fields = getFields(model.getClass());

        for (int i = 0; i < fields.length ; i++) {
            Field field = fields[i];

            Where where = field.getAnnotation(Where.class);
            if(where==null) continue;

            Object fieldValue = getFieldValue(field,model);
            //处理字符串为空字符串，如果为空字符串那么就不用构建该属性了
            if (fieldValue instanceof String && StringUtils.isEmpty(fieldValue)) {
                fieldValue = null;
            }
            //过滤NULL值
            if(fieldValue==null) continue;

            IQueryExpression expression = QueryExpressionFactory.create(where.value());
            if(expression==null) continue;

            //执行where子句
            String columnName = CaseUtils.camelToUnderline(field.getName());
            expression.where(query,columnName,fieldValue);
        }

        return query;
    }
    public static <T> QueryWrapper<T> sort(QueryWrapper<T> query, Object model){

        if(model==null) return null;

        Field[] fields = getFields(model.getClass());

        List<SortField> sortFields = new ArrayList<>();
        for (int i = 0; i < fields.length ; i++) {
            Field field = fields[i];
            Sort sort = field.getAnnotation(Sort.class);
            if(sort==null) continue;
            String columnName = CaseUtils.camelToUnderline(field.getName());
            sortFields.add(new SortField(columnName,sort));
        }

        sortFields.sort((f1,f2)-> f2.getSort().order() - f1.getSort().order());

        for (int i = 0; i < sortFields.size(); i++) {
            SortField sortField = sortFields.get(i);
            if(sortField.getSort().value() == SortDirection.ASC){
                query.orderByAsc(sortField.getName());
            }
            else {
                query.orderByDesc(sortField.getName());
            }
        }
        return query;
    }

    static Field[] getFields(Class c){
        List<Field> list = new ArrayList<>();
        if(c.getSuperclass() !=null){
            Field[] parents = getFields(c.getSuperclass());
            list.addAll(Arrays.asList(parents));
        }
        Field[] fields = c.getDeclaredFields();
        list.addAll(Arrays.asList(fields));
        return list.toArray(new Field[list.size()]);
    }
    static Object getFieldValue(Field declaredField, Object model){
        /**
         * 获取属性值
         */
        Object value = null;
        try {
            boolean old = declaredField.isAccessible();
            if(!old)
                declaredField.setAccessible(true);
            value = declaredField.get(model);
            declaredField.setAccessible(old);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return value;
    }
}
