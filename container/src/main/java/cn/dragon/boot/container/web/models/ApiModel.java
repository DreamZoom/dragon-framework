package cn.dragon.boot.container.web.models;

import cn.dragon.boot.container.web.Handler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class ApiModel implements Serializable {



    private String id;
    private String name;

    @JsonIgnore
    private Handler handler;

    public ApiModel(String id,String name, Handler handler) {
        this.id = id;
        this.name = name;
        this.handler = handler;
    }
    public String getName() {
        return name;
    }


    public Handler getHandler() {
        return handler;
    }
    public String getId() {
        return id;
    }

}
