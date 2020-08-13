#### swagger2开启
1. pom.xml
    ```xml
        <!-- swagger2 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
    ```
2. 增加config配置
    ```java
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
    ```  
3.  增加注解
4. 访问地址
    http://localhost:port/swagger-ui.html