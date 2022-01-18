package cn.dragon.boot.container.web.filter;


import cn.dragon.framework.web.Handler;
import cn.dragon.framework.web.HandlerContext;

import java.util.ArrayList;
import java.util.List;

public class ApplicationFilterChain implements FilterChain {


    private List<Filter> filters;
    private Handler handler;
    private int pos = 0;

    public ApplicationFilterChain(Handler handler) {
        this.handler = handler;
        this.filters = new ArrayList<>();
        this.addFilter(new TokenFilter());
    }

    public void addFilter(Filter filter){
        this.filters.add(filter);
    }


    @Override
    public void doFilter(HandlerContext context) throws Throwable {
        int size = filters.size();

        if(size>pos){
            Filter filter = filters.get(pos++);
            filter.doFilter(context,this);
            return;
        }
        try {
           Object returnValue = handler.handle(context);
           context.setReturnValue(returnValue);
        } catch (Throwable throwable) {
            throw throwable;
        }
    }
}
