package cn.dragon.cloud.cms.domain;

import cn.dragon.framework.domain.BaseEntity;

public class WebPage extends BaseEntity {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
}
