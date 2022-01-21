package cn.dragon.cloud.cms.domain;

import cn.dragon.framework.domain.BaseEntity;

public class Category extends BaseEntity {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
