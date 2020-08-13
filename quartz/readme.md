### Quartz
1. pom.xml
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-quartz</artifactId>
    </dependency>
    ```
2. 启动加注解
    ```java
    @EnableScheduling
    ```    
3. 创建job（两种方式）
    1. 直接创建bean
        >首先将这个 Job 注册到 Spring 容器中。
        >> 这种定义方式有一个缺陷，就是无法传参。
    2. 继承QuartzJobBean 并实现默认的方法
        >这种凡是支持传参,任务启动时，executeInternal 方法将会被执行