package cn.dragon.boot.container.web.filter;

import cn.dragon.framework.Api;
import cn.dragon.framework.security.RoleDetails;
import cn.dragon.framework.security.RoleLoader;
import cn.dragon.framework.security.Token;
import cn.dragon.framework.web.HandlerContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Component
@Order(20)
public class SecurityAccessFilter implements Filter {

    @Resource
    RoleLoader roleLoader;

    @Override
    public void doFilter(HandlerContext context, FilterChain chain) throws Throwable {

        Api api = AnnotationUtils.findAnnotation(context.getHandler().getMethod(), Api.class);

        if(!api.isPublic()){
            Token token = (Token) context.getAttribute(Token.class);

            if(token==null){
                sendError(context,401,"未授权");
                return;
            }

            List<RoleDetails> roles = roleLoader.load(token);

            boolean checked = false;
            for (int i = 0; i < roles.size(); i++) {
                checked = roles.get(i).check(context);
                if(checked) break;
            }

            if(!checked){
                sendError(context,403,"访问拒绝");
                return;
            }
        }
        chain.doFilter(context);
    }

    void sendError(HandlerContext context,int status,String message) throws IOException {
        context.getHttpServletResponse().sendError(status,message);
    }
}
