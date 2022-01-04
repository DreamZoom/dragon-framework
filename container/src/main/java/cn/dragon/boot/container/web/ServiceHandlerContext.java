package cn.dragon.boot.container.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;


public class ServiceHandlerContext implements HandlerContext {
    public ServiceHandlerContext(HttpServletRequest request) {
        this.request = request;
    }

    private HttpServletRequest request;
    @Override
    public Object getParameter(String name,Class type) {

        String contentType =  request.getContentType();
        if("application/json".equals(contentType)){
            try{
                ObjectMapper mapper =new ObjectMapper();
                Map<String,Object> jsonObject = mapper.readValue(request.getInputStream(),Map.class);
                if(jsonObject.containsKey(name)){
                    Object value = jsonObject.get(name);
                    return mapper.convertValue(value,type);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!StringUtils.isEmpty(contentType) && contentType.indexOf("multipart/form-data;")>=0){

            
        }


        return request.getParameter(name);
    }
}
