package cn.dragon.boot.container.web;

import cn.dragon.boot.container.utils.SpringUtils;
import cn.dragon.boot.container.web.models.ApiModel;
import cn.dragon.boot.container.web.models.ServiceModel;
import cn.dragon.framework.Api;
import cn.dragon.framework.ApiService;
import cn.dragon.framework.IDragonService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ServiceHandlerRegistry implements HandlerRegistry, InitializingBean {


    private String SERVICE_NAME_HEADER = "Service-Name";
    private String SERVICE_METHOD_HEADER = "Service-Method";


    private List<ServiceModel> services = new ArrayList<>();;

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
                Object bean = getApplicationContext().getBean(beanType);
                detectHandlerMethods(bean,beanType);
            }
        }
    }

    protected boolean isApiService(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, ApiService.class);
    }
    protected boolean isApi(Method method) {
        return AnnotatedElementUtils.hasAnnotation(method, Api.class);
    }
    protected void detectHandlerMethods(Object bean,Class<?> beanType){
        final Class<?> userType = ClassUtils.getUserClass(beanType);


        Map<Method, HandlerMethod> methods = MethodIntrospector.selectMethods(userType, new MethodIntrospector.MetadataLookup<HandlerMethod>() {
            @Override
            public HandlerMethod inspect(Method method) {
                try {
                    return getHandlerForMethod(bean,method);
                }
                catch (Throwable ex) {
                    throw new IllegalStateException("Invalid mapping on handler class [" +
                            userType.getName() + "]: " + method, ex);
                }
            }
        });

        ApiService apiService = AnnotationUtils.findAnnotation(userType,ApiService.class);
        ServiceModel serviceModel = new ServiceModel(userType.getName(),apiService.name());
        methods.forEach((method,handlerMethod)->{
            Api api = AnnotationUtils.findAnnotation(method,Api.class);
            serviceModel.addApi(new ApiModel(method.getName(),api.name(),new ServiceHandler(bean,method)));
        });
        services.add(serviceModel);
    }

    protected HandlerMethod getHandlerForMethod(Object bean,Method method) {
        if(isApi(method)){
            return new HandlerMethod(bean,method);
        }
        return null;
    }


    private ApplicationContext getApplicationContext(){
        return  springUtils.getApplicationContext();
    }

    @Override
    public Handler getHandler(HttpServletRequest request) {
        String serviceName = request.getHeader(SERVICE_NAME_HEADER);
        String methodName = request.getHeader(SERVICE_METHOD_HEADER);
        ServiceModel service =null;

        for (ServiceModel serviceModel: services) {
            if(serviceModel.getId().equals(serviceName)){
                service = serviceModel;
            }
        }
        if(service == null) return null;
        for (ApiModel api: service.getApis()) {
            if(api.getId().equals(methodName)){
                return  api.getHandler();
            }
        }
        return null;
    }

    @Override
    public Object getData() {
        return services;
    }
}
