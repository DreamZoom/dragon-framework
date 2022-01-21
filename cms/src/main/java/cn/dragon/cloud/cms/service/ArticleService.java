package cn.dragon.cloud.cms.service;

import cn.dragon.cloud.cms.domain.Article;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.service.BaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


@ApiService(name = "文章服务")
public class ArticleService extends BaseService<Article,String> {

    @Api(name = "文章更新")
    @Override
    public Article save(Article model) throws Exception {
        return super.save(model);
    }

    @Api(name = "文章查询")
    @Override
    public Article find(String id) {
        return super.find(id);
    }

    @Api(name = "文章删除")
    @Override
    public void delete(String id) throws Exception {
        super.delete(id);
    }

    @Api(name = "文章分页查询")
    @Override
    public Page<Article> queryAll(Article model, Page page) {
        return super.queryAll(model, page);
    }
}
