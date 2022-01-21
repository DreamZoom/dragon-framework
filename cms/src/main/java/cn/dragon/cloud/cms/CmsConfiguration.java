package cn.dragon.cloud.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.dragon.cloud.cms.repository")
public class CmsConfiguration {
}
