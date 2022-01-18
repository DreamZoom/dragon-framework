package cn.dragon.framework.web.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiceModel implements Serializable {




    private String name;
    private String id;
    private List<ApiModel> apis = new ArrayList<>();;

    public ServiceModel(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public void addApi(ApiModel model){
        apis.add(model);
    }
    public String getName() {
        return name;
    }

    public List<ApiModel> getApis() {
        return apis;
    }

    public String getId() {
        return id;
    }

}
