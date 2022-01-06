package cn.dragon.boot.container.api;

import cn.dragon.boot.container.utils.SpringUtils;
import cn.dragon.boot.container.web.*;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.IDragonService;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import java.util.Map;

/**
 * api端点 所有服务调用的入口
 */
@RestController
public class EndpointController {


    @Resource
    HandlerRegistry handlerRegistry;

    /**
     * /api 接口
     * @param service 服务名称，通常为服务完整包名
     * @param method 方法名称，调用的接口
     * @param request 请求上下文，自动注入，无需传参
     * @return 服务调用结果
     * @throws Exception 可能的异常
     */
    @PostMapping("/api")
    public Object invoke(HttpServletRequest request) throws Throwable {
        Handler handler =handlerRegistry.getHandler(request);
        HandlerContext context =new ServiceHandlerContext(request);
        return handler.handle(context);
    }

    @GetMapping("/services")
    public Object services() {
        Object services =handlerRegistry.getData();
        return services;
    }

}
