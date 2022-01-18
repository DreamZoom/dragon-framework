package cn.dragon.boot.container.web.filter;

import cn.dragon.framework.security.AuthenticationToken;
import cn.dragon.framework.security.GuestToken;
import cn.dragon.framework.security.Token;
import cn.dragon.framework.web.HandlerContext;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class TokenFilter implements Filter {

    private static String TokenKey = "Authorization";
    private static String TokenTypeKey = "Bearer";


    @Override
    public void doFilter(HandlerContext context, FilterChain chain) throws Throwable {
        HttpServletRequest request = context.getHttpServletRequest();
        String token = request.getHeader(TokenKey);
        if(StringUtils.isEmpty(token)) {
            context.setAttribute(Token.class,new GuestToken());
        }
        else{
            token = token.replace(TokenTypeKey,"").trim();
            context.setAttribute(Token.class,new AuthenticationToken(token));
        }
        chain.doFilter(context);
    }
}
