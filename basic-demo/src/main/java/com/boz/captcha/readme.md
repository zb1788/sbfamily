### Captcha使用步骤
1. pom.xml导入依赖
```xml
        <!--验证码 -->
        <dependency>
            <groupId>com.github.penggle</groupId>
            <artifactId>kaptcha</artifactId>
            <version>2.3.2</version>
            <exclusions>
                <exclusion>
                    <artifactId>javax.servlet-api</artifactId>
                    <groupId>javax.servlet</groupId>
                </exclusion>
            </exclusions>
        </dependency>
```
2. 复制config和utils类
3. 在config中的CapchaConfig类中搜索kaptcha.textproducer.impl
```
#修改为当前工具类的全路径
properties.setProperty("kaptcha.textproducer.impl", "com.boz.captcha.utils.KaptchaTextCreator");
```