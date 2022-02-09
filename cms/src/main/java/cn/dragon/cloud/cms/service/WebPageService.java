package cn.dragon.cloud.cms.service;

import cn.dragon.cloud.cms.domain.Category;
import cn.dragon.cloud.cms.domain.WebPage;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.service.BaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


@ApiService(name = "网页服务")
public class WebPageService extends BaseService<WebPage,String> {

    @Api(name = "网页更新")
    @Override
    public WebPage save(WebPage model) throws Exception {
        return super.save(model);
    }

    @Api(name = "网页查询")
    @Override
    public WebPage find(String id) {
        return super.find(id);
    }

    @Api(name = "网页删除")
    @Override
    public void delete(String id) throws Exception {
        super.delete(id);
    }

    @Api(name = "网页分页查询")
    @Override
    public Page<WebPage> queryAll(WebPage model, Page page) {
        return super.queryAll(model, page);
    }
}
