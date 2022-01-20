package cn.dragon.boot.container.api;

import cn.dragon.boot.container.utils.SpringUtils;
import cn.dragon.boot.container.web.*;
import cn.dragon.boot.container.web.filter.ApplicationFilterChain;
import cn.dragon.boot.container.web.filter.Filter;
import cn.dragon.framework.web.Handler;
import cn.dragon.framework.web.HandlerContext;
import cn.dragon.framework.web.HandlerRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * api端点 所有服务调用的入口
 */
@RestController
public class EndpointController {


    @Resource
    HandlerRegistry handlerRegistry;

    @Resource
    SpringUtils springUtils;

    @Resource
    List<Filter> filters;

    /**
     * /api 接口
     * @param service 服务名称，通常为服务完整包名
     * @param request 请求上下文，自动注入，无需传参
     * @return 服务调用结果
     * @throws Exception 可能的异常
     */
    @PostMapping("/api")
    public Object invoke(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        Handler handler =handlerRegistry.getHandler(request);
        HandlerContext context =new ServiceHandlerContext(request,response, springUtils.getApplicationContext(), handler);
        ApplicationFilterChain chain = new ApplicationFilterChain(handler, filters);
        chain.doFilter(context);
        return context.getReturnValue();
    }

    @GetMapping("/services")
    public Object services() {
        Object services =handlerRegistry.getData();
        return services;
    }

}
