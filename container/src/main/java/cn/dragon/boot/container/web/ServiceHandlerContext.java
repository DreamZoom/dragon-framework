package cn.dragon.boot.container.web;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class ServiceHandlerContext implements HandlerContext {
    public ServiceHandlerContext(HttpServletRequest request) {
        this.request = request;
    }

    private HttpServletRequest request;
    @Override
    public Object getParameter(String name,Class type) {


        return request.getParameter(name);
    }
}
