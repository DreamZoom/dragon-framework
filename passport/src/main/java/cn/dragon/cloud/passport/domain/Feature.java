package cn.dragon.cloud.passport.domain;

import cn.dragon.framework.domain.BaseEntity;

/**
 * 功能模型
 */
public class Feature extends BaseEntity {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApis() {
        return apis;
    }

    public void setApis(String apis) {
        this.apis = apis;
    }

    private String name;
    private String apis;
}
