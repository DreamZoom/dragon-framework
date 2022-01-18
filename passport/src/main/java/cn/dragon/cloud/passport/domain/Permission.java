package cn.dragon.cloud.passport.domain;

import cn.dragon.framework.domain.BaseEntity;

/**
 * 功能模型
 */
public class Permission extends BaseEntity {
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private String name;

    private String apis;
}
