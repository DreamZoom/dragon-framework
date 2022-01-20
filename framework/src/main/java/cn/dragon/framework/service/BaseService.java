package cn.dragon.framework.service;


import cn.dragon.framework.Api;
import cn.dragon.framework.IDragonService;
import cn.dragon.framework.domain.BaseEntity;
import cn.dragon.framework.exception.ApiException;
import cn.dragon.framework.exception.ServiceException;
import cn.dragon.framework.query.QueryBuilder;
import cn.dragon.framework.repository.BaseRepository;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseService<T extends BaseEntity,ID extends Serializable> implements IDragonService {

    @Autowired
    protected BaseRepository<T,ID> repository;

    public T save(T entity) throws Exception{
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if(!StringUtils.hasText(entity.getId())){
            /**
             * create_time 在数据库层设置为当前时间 CURRENT_TIMESTAMP
             * update_time 在数据库层设置为当前时间 CURRENT_TIMESTAMP 在更新时
             */
            entity.setCreateTime(timestamp);
            entity.setUpdateTime(timestamp);
            repository.insert(entity);
        }
        else{
            entity.setCreateTime(null);
            entity.setUpdateTime(timestamp);
            repository.updateById(entity);
        }
        return repository.selectById(entity.getId());
    }

    public List<T> save(Iterable<T> entities) throws Exception{
        for (T e:entities) {
            this.save(e);
        }
        return (List<T>) entities;
    }



    @Transactional
    public T update(T entity) throws Exception {
        if(!StringUtils.hasText(entity.getId())){
            throw new ServiceException("参数不全,id是必要参数");
        }
        T model = this.find((ID) entity.getId());
        if(model==null) {
            throw new ServiceException("记录不存在");
        }
        return this.save(entity);
    }

    public void delete(T entity) throws Exception {
        this.delete((ID) entity.getId());
    }

    public void delete(ID id) throws ApiException, Exception {
        repository.deleteById((Serializable) id);
    }
    public void delete(ID[] ids){
        repository.deleteBatchIds(Arrays.asList(ids));
    }
    public void delete(Iterable<? extends T> entities){
        List<ID> ids = new ArrayList<>();
        for (T m:entities) {
            ids.add((ID) m.getId());
        }
        repository.deleteBatchIds(ids);
    }

    public T find(ID id){
        return  repository.selectById(id);
    }

    public List<T> findByKeys(String key,Object ...values){
        QueryWrapper<T> queryWrapper = Wrappers.query();
        queryWrapper.in(key,values);
        return repository.selectList(queryWrapper);
    }

    public T findByFieldValue(String field,Object value){
        QueryWrapper<T> wrapper = Wrappers.query();
        wrapper.eq(field,value);
        return repository.selectOne(wrapper);
    }


    public List<T> queryAll(T model){
        return  repository.selectList(QueryBuilder.build(model));
    }

    public Page<T> queryAll(QueryWrapper<T> queryWrapper, Page page){
        return repository.selectPage(page,queryWrapper);
    }


    public Page<T> queryAll(T model,Page page){
        return  this.queryAll(QueryBuilder.build(model),page);
    }


}
