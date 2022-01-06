package cn.dragon.boot.container.web;

import java.lang.reflect.Method;

/***
 * 多态方法调用
 * 针对方法名称相同，参数签名不同。（待实现）
 */
public class ServiceHandlerChain extends ServiceHandler {
    public ServiceHandlerChain(Object bean, Method method) {
        super(bean, method);
    }
}
