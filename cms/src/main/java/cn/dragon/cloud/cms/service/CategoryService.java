package cn.dragon.cloud.cms.service;

import cn.dragon.cloud.cms.domain.Article;
import cn.dragon.cloud.cms.domain.Category;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.service.BaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


@ApiService(name = "文章类别服务")
public class CategoryService extends BaseService<Category,String> {

    @Api(name = "文章类别更新")
    @Override
    public Category save(Category model) throws Exception {
        return super.save(model);
    }

    @Api(name = "文章类别查询")
    @Override
    public Category find(String id) {
        return super.find(id);
    }

    @Api(name = "文章类别删除")
    @Override
    public void delete(String id) throws Exception {
        super.delete(id);
    }

    @Api(name = "文章类别分页查询")
    @Override
    public Page<Category> queryAll(Category model, Page page) {
        return super.queryAll(model, page);
    }
}
