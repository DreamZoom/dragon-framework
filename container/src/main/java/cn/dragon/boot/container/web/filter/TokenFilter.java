package cn.dragon.boot.container.web.filter;

import cn.dragon.framework.security.GuestToken;
import cn.dragon.framework.security.Token;
import cn.dragon.framework.security.TokenService;
import cn.dragon.framework.web.HandlerContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class TokenFilter implements Filter {

    private static String TokenKey = "Authorization";
    private static String TokenTypeKey = "Bearer";

    @Resource
    TokenService tokenService;

    @Override
    public void doFilter(HandlerContext context, FilterChain chain) throws Throwable {
        HttpServletRequest request = context.getHttpServletRequest();
        String token = request.getHeader(TokenKey);
        if(StringUtils.isEmpty(token)) {
            context.setAttribute(Token.class,new GuestToken());
        }
        else{
            try {
                token = token.replace(TokenTypeKey,"").trim();
                Token loadToken = tokenService.loadToken(token);
                context.setAttribute(Token.class,loadToken);
            }
            catch (Exception err){
                //认证异常
                context.getHttpServletResponse().sendError(401,err.getMessage());
            }

        }
        chain.doFilter(context);
    }
}
