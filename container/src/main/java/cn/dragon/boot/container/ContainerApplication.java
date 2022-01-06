package cn.dragon.boot.container;

import cn.dragon.boot.container.utils.ClassNameBeanNameGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"cn.dragon.cloud.passport","cn.dragon.boot.container"},nameGenerator = ClassNameBeanNameGenerator.class)
@SpringBootApplication
public class ContainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContainerApplication.class,args);
    }
}
