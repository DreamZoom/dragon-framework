package cn.dragon.boot.container.api;

import cn.dragon.boot.container.utils.SpringUtils;
import cn.dragon.cloud.passport.service.UserService;
import cn.dragon.framework.IDragonService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

@RestController
public class EndpointController {


    @Resource
    SpringUtils springUtils;

    @Resource
    UserService userService;

    @RequestMapping("/api")
    public Object invoke(String service, String method, ServerWebExchange request) throws Exception {
        ApplicationContext context = springUtils.getApplicationContext();
        //获取所有的Service

        Map<String, IDragonService> services = context.getBeansOfType(IDragonService.class);

        if(services.containsKey(service)){
            IDragonService dragonService = services.get(service);
            Method[] methods = dragonService.getClass().getDeclaredMethods();
            for (Method m: methods) {
//                AnnotationUtils.findAnnotation()
                if (m.getName().equals(method)){

                    Parameter[] parameters = m.getParameters();
                    Object[] args = new Object[parameters.length];
                    for (int i = 0; i <parameters.length ; i++) {
                        MethodParameter  parameter =new MethodParameter(m,i+1);
                        args[i]=resolveArgument(parameter,request);
                    }
                    return m.invoke(dragonService,args);
                }
            }

        }
        return "method not find";
    }

    Object resolveArgument(MethodParameter parameter, ServerWebExchange request){
        MultiValueMap map = request.getRequest().getQueryParams();
        if(map.containsKey(parameter.getParameterName())){
            return map.getFirst(parameter.getParameterName());
        }
        return null;
    }
}
