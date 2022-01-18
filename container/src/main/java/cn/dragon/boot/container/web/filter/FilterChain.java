package cn.dragon.boot.container.web.filter;


import cn.dragon.framework.web.HandlerContext;

public interface FilterChain {

    void doFilter(HandlerContext context) throws Throwable;
}
