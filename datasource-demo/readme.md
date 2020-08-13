1. ### 动态切换数据
    1. 最重要启动类上要加这个注解,否则报循环引用类
    ```java
    @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
    
    ```
    2. 创建注解类
    3. 继承AbstractRoutingDataSource，通过这个来实现数据库切换
    4. 使用ThreadLocal切换数据源
    5. 初始化数据源
    5. 添加注解使用

2. 用mybatisPlus的切换数据源插件
    1. 引入依赖
        ```xml
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
                <version>2.5.4</version>
            </dependency>
        ```
    2. application.yml配置多数据源
        ```yaml
        spring:
          profiles:
            active: dev
          datasource:
            dynamic:
              primary: master
              strict: false
              datasource:
                master:
                  url: jdbc:mysql://192.168.151.126:3306/badmin?useUnicode=true&characterEncoding=utf-8&useAffectedRows=true&serverTimezone=UTC
                  username: root
                  password: 123456
                  driver-class-name: com.mysql.jdbc.Driver
                slave_1:
                  url: jdbc:mysql://192.168.151.208:3306/test?useUnicode=true&characterEncoding=utf-8&useAffectedRows=true&serverTimezone=UTC
                  username: vcom
                  password: yjt_yyl20160309
                  driver-class-name: com.mysql.jdbc.Driver
        
        logging:
          level:
            com.baomidou.dynamic: debug
        ```
    3. 使用方法
       启动类排除原生Druid的快速配置类@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
       
       在控制器，方法或者service或者mapper上加@Ds注解选择数据源