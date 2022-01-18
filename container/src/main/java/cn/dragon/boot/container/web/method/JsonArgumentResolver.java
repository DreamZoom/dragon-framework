package cn.dragon.boot.container.web.method;

import cn.dragon.framework.web.HandlerContext;
import cn.dragon.framework.web.HandlerParameter;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class JsonArgumentResolver implements HandlerArgumentResolver {

    private Map<String,Object> jsonBody = null;

    @Override
    public boolean supportsParameter(HandlerParameter parameter) {
        return true;
    }

    @Override
    public Object resolveArgument(HandlerParameter parameter, HandlerContext context) throws Exception {
        String name =parameter.getParameterName();
        Class type = parameter.getParameterType();

        HttpServletRequest request = context.getHttpServletRequest();
        String contentType =  request.getContentType();

        //类型转化器
        ObjectMapper mapper =new ObjectMapper();
        if("application/json".equals(contentType)){
            if(jsonBody==null){
                try{
                    jsonBody = mapper.readValue(request.getInputStream(), Map.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(jsonBody.containsKey(name)){
                Object value = jsonBody.get(name);
                return mapper.convertValue(value,type);
            }
        }
        return null;
    }
}
