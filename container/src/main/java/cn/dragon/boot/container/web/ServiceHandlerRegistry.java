package cn.dragon.boot.container.web;

import cn.dragon.boot.container.utils.SpringUtils;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.IDragonService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceHandlerRegistry implements HandlerRegistry, InitializingBean {

    private Map<String,Handler> mappingRegistry =new HashMap<>();

    @Resource
    SpringUtils springUtils;

    @Override
    public void afterPropertiesSet() throws Exception {
        initHandlerMethods();
    }



    protected void initHandlerMethods() {
        String[] beanNames = getApplicationContext().getBeanNamesForType(IDragonService.class);
        for (String beanName : beanNames) {
            Class<?> beanType = getApplicationContext().getType(beanName);
            if(isApiService(beanType)){
                detectHandlerMethods(beanType);
            }
        }
    }

    protected boolean isApiService(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, ApiService.class);
    }
    protected boolean isApi(Method method) {
        return AnnotatedElementUtils.hasAnnotation(method, Api.class);
    }
    protected void detectHandlerMethods(Class<?> beanType){
        final Class<?> userType = ClassUtils.getUserClass(beanType);
        System.out.println(userType.getName());
        Map<Method, Handler> methods = MethodIntrospector.selectMethods(userType,
                new MethodIntrospector.MetadataLookup<Handler>() {
                    @Override
                    public Handler inspect(Method method) {
                        try {
                            //2. 根据方法上的@RequestMapping信息构建RequestMappingInfo
                            return getHandlerForMethod(method, userType);
                        }
                        catch (Throwable ex) {
                            throw new IllegalStateException("Invalid mapping on handler class [" +
                                    userType.getName() + "]: " + method, ex);
                        }
                    }
                });
    }

    protected Handler getHandlerForMethod(Method method, Class<?> handlerType) {
        System.out.println(method);
        if(isApi(method)){
            return new ServiceHandler(null,method);
        }
        return null;
    }


    private ApplicationContext getApplicationContext(){
        return  springUtils.getApplicationContext();
    }
}
