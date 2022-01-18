package cn.dragon.framework.web.models;

import java.io.Serializable;

public class ParameterModel implements Serializable {

    public ParameterModel(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    private Class<?> type;
}
