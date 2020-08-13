### yml文件相关解析
1. 单个属性解析
    >@Value解析
```java    
    //@Value("${boz.projectInfo:#{null}}")
    //:#{null}作用为在取不到对应配置值时，采用默认值null赋值
    @Value("${boz.name}")
    String projectName;
```    
2. 以对象方式获取
    1. 创建对应属性的bean
        > demo:entity/Project
    2. 创建对应的config初始化bean
        > demo:config/ProjectConfig
    3. Controller注入bean
    ```java
    @Autowired
    private Project project;
    ```
3. yml工具类
    1. jar包依赖
        ```xml
            <dependency>
                <groupId>org.yaml</groupId>
                <artifactId>snakeyaml</artifactId>
            </dependency>
        ```
    2. 通过yml工具类读取yml文件内容
        > demo:YmlControlelr->readYmlByTools
        ```java
        yamlMap = YamlUtil.loadYaml("application.yml");
        ```
    3. 通过yml工具类写入yml文件
        > demo:YmlController->writeYmlByTools
        ```java
        YamlUtil.dumpYaml(Global.getProfile()+"/1/2/test.yml",yamlMap);
        ```