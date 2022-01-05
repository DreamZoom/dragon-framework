package cn.dragon.boot.container.api;

import cn.dragon.boot.container.utils.SpringUtils;
import cn.dragon.boot.container.web.Handler;
import cn.dragon.boot.container.web.HandlerContext;
import cn.dragon.boot.container.web.ServiceHandler;
import cn.dragon.boot.container.web.ServiceHandlerContext;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.IDragonService;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
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
    SpringUtils springUtils;

    /**
     * /api 接口
     * @param service 服务名称，通常为服务完整包名
     * @param method 方法名称，调用的接口
     * @param request 请求上下文，自动注入，无需传参
     * @return 服务调用结果
     * @throws Exception 可能的异常
     */
    @RequestMapping("/api")
    public Object invoke(String service, String method, HttpServletRequest request) throws Exception {
        Handler handler =getHandler(service,method);
        HandlerContext context =new ServiceHandlerContext(request);
        return handler.handle(context);
    }


    Handler getHandler(String service,String method){
        ApplicationContext context = springUtils.getApplicationContext();
        IDragonService dragonService = (IDragonService) context.getBean(service);
        if(dragonService != null){
            //检测服务是否具有ApiService注解
            Class serviceClass = AopUtils.getTargetClass(dragonService);
            ApiService apiService = AnnotationUtils.findAnnotation(serviceClass,ApiService.class);
            if(apiService == null) return null;

            Method[] methods = serviceClass.getDeclaredMethods();
            for (Method myMethod: methods) {
                Api api = AnnotationUtils.findAnnotation(myMethod,Api.class);
                if (myMethod.getName().equals(method) && api!=null) {
                    return new ServiceHandler(dragonService,myMethod);
                }
            }
        }
        return null;
    }

}
