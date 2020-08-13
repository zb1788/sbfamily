### I18n国际化配置
1. application.yml增加配置
```
spring:
  messages:
    basename: i18n/messages
```
2. 在resources中新建文件夹i18n
3. 新建文件messages.properties,messages_zh_CN.properties,messages_en_US.properties
    > 如果仅有中文只用新建messages.properties就可以
4. 增加I18nConfig用于设置当前会话的默认国际化语言
   > 默认拦截器：LocaleChangeInterceptor 指定切换国际化语言的参数名。例如?lang=zh代表读取messages_zh_CN.properties文件
   >>文件位置config/I18nConfig
5. 修改默认语言
    > 调用控制器传递参数lang
    >> 例子：I18TestController中的changeSessionLanguage