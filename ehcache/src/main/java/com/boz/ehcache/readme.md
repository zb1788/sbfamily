### ehcache环境搭建
1. pom.xml

    ```xml
    <!-- Spring Boot 缓存支持启动器 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
    <!-- Ehcache 坐标 -->
    <dependency>
        <groupId>net.sf.ehcache</groupId>
        <artifactId>ehcache</artifactId>
    </dependency>
    
    ```

2. application.yml配置
    ```yaml
    spring:
      cache:
        ehcache:
          config: classpath:ehcache.xml
    ```
3. resources里下增加ehcache配置文件
4. 启动类开启注解
    ```java
    @EnableCaching
    ```
5. 在方法或者控制器等上增加缓存注解