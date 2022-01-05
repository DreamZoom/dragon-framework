package cn.dragon.framework.domain;


import cn.dragon.framework.query.Sort;
import cn.dragon.framework.query.SortDirection;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.sql.Timestamp;

public class BaseEntity implements Serializable , IQueryModel {
    private static final long serialVersionUID = 530L;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private  String id;

    @Sort(value = SortDirection.ASC)
    @TableField(fill = FieldFill.INSERT)
    protected  Timestamp createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected  Timestamp updateTime;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    protected  Integer sort;


    @Override
    public String toString() {
        return "BaseEntity{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sort='" + sort + '\'' +
                '}';
    }
}
