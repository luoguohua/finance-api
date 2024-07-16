package com.luoguohua.finance.framework.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/10 14:02
 * Content:
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI localOpenAPI(){
        return new OpenAPI().info(
                new Info()
                        .title("账务管理信息系统API")
                        .description("账务管理信息系统")
                        .version("v1.0.0")
                        .license(new License().name("许可协议").url("https://www.baidu.com"))
                        .contact(new Contact().name("luogh").email("luoguohua_xin@163.com"))

        ).externalDocs(
                new ExternalDocumentation()
                        .description("账务管理信息系统")
                        .url("http://localhost:8080/")
        ).security(List.of(new SecurityRequirement().addList("api_token")));
    }
}
