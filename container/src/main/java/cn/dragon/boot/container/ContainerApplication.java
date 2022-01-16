package cn.dragon.boot.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@ComponentScan(basePackages = {"cn.dragon.cloud.passport","cn.dragon.boot.container"})
@SpringBootApplication
public class ContainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContainerApplication.class,args);
    }
}
