package cn.dragon.cloud.passport.domain;

import cn.dragon.framework.domain.BaseEntity;


/**
 * 角色模型
 */
public class Role extends BaseEntity {

    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
            ", name=" + name +
            ", description=" + description +
        "}";
    }
}
