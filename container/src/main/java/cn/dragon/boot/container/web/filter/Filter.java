package cn.dragon.boot.container.web.filter;


import cn.dragon.framework.web.HandlerContext;

public interface Filter {
    void doFilter(HandlerContext context, FilterChain chain) throws Throwable;
}
