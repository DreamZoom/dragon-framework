package cn.dragon.boot.container.api;

import cn.dragon.boot.container.utils.SpringUtils;
import cn.dragon.boot.container.web.Handler;
import cn.dragon.boot.container.web.HandlerContext;
import cn.dragon.boot.container.web.ServiceHandler;
import cn.dragon.boot.container.web.ServiceHandlerContext;
import cn.dragon.framework.IDragonService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import java.util.Map;

@RestController
public class EndpointController {


    @Resource
    SpringUtils springUtils;


    @RequestMapping("/api")
    public Object invoke(String service, String method, HttpServletRequest request) throws Exception {
        Handler handler =getHandler(service,method);
        HandlerContext context =new ServiceHandlerContext(request);
        return handler.handle(context);
    }


    Handler getHandler(String service,String method){
        ApplicationContext context = springUtils.getApplicationContext();
        Map<String, IDragonService> services = context.getBeansOfType(IDragonService.class);
        if(services.containsKey(service)){
            IDragonService dragonService = services.get(service);
            Method[] methods = dragonService.getClass().getDeclaredMethods();
            for (Method myMethod: methods) {
                if (myMethod.getName().equals(method)) {
                    return new ServiceHandler(dragonService,myMethod);
                }
            }
        }
        return null;
    }
}
