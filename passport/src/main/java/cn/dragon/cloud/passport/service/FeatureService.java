package cn.dragon.cloud.passport.service;

import cn.dragon.cloud.passport.domain.Feature;
import cn.dragon.cloud.passport.domain.Role;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.service.BaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@ApiService(name = "功能服务")
public class FeatureService  extends BaseService<Feature,String> {
    @Api(name = "功能更新")
    @Override
    public Feature save(Feature model) throws Exception {
        return super.save(model);
    }

    @Api(name = "功能查询")
    @Override
    public Feature find(String id) {
        return super.find(id);
    }

    @Api(name = "功能删除")
    @Override
    public void delete(String id) {
        super.delete(id);
    }

    @Api(name = "功能分页查询")
    @Override
    public Page<Feature> queryAll(Feature model, Page page) {
        return super.queryAll(model, page);
    }
}
