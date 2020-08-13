package com.boz.ehcache.swagger.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author boz
 * @date 2019/8/16
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select()
                .apis(RequestHandlerSelectors.basePackage("com.boz.ehcache.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("Springboot整合Swapper")
                        .description("Springboot整合swagger的详细信息")
                        .version("1.0.0")
                        .contact(new Contact("name","adfsa","abc@qq.com"))
                        .license("license")
                        .licenseUrl("http://www.baidu.com")
                        .build());
    }
}
