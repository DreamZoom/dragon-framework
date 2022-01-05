package cn.dragon.boot.container.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ServerWebExchange;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ServiceHandlerContext implements HandlerContext {

    private Map<String,Object> jsonBody = null;

    public ServiceHandlerContext(HttpServletRequest request) {
        this.request = request;
    }

    private HttpServletRequest request;
    @Override
    public Object getParameter(HandlerParameter parameter) {
        String name =parameter.getParameterName();
        Class type = parameter.getParameterType();
        String contentType =  request.getContentType();

        //类型转化器
        ObjectMapper mapper =new ObjectMapper();

        if("application/json".equals(contentType)){
            if(jsonBody==null){
                try{
                    jsonBody = mapper.readValue(request.getInputStream(),Map.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(jsonBody.containsKey(name)){
                Object value = jsonBody.get(name);
                return mapper.convertValue(value,type);
            }
        }

        if(!StringUtils.isEmpty(contentType) && contentType.indexOf("multipart/form-data;")>=0 && MultipartFile.class.equals(type)){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile(name);
            return file;
        }

        //从参数转化类型
        String valueString = request.getParameter(name);
        if(StringUtils.isEmpty(valueString)) return null;
        return mapper.convertValue(valueString,type);
    }
}
