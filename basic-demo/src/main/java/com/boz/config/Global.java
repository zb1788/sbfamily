package com.boz.config;

import com.boz.utils.YamlUtil;
import com.boz.utils.text.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类
 * @author boz
 * @date 2019/7/10
 */
@Slf4j
public class Global {

    private static String NAME = "application.yml";

    /**
     * 当前对象实例
     */
    private static Global global;

    /**
     * 保存全局属性值
     */
    private static Map<String ,String> map = new HashMap<>();

    private Global(){

    }

    /**
     * 静态工厂方法
     * @return
     */
    public static synchronized Global getInstance(){
        if(global == null){
            global = new Global();
        }
        return global;
    }


    /**
     * 获取配置
     */
    public static String getConfig(String key)
    {
        String value = map.get(key);
        if (value == null)
        {
            Map<?, ?> yamlMap = null;
            try
            {
                yamlMap = YamlUtil.loadYaml(NAME);
                value = String.valueOf(YamlUtil.getProperty(yamlMap, key));
                map.put(key, value != null ? value : StringUtils.EMPTY);
            }
            catch (FileNotFoundException e)
            {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return value;
    }

    /**
     * 获取项目名称
     * @return
     */
    public static String getNAME(){
        return StringUtils.nvl(getConfig("boz.name"),"boz");
    }

    /**
     * 获取版本
     * @return
     */
    public static String getVersion(){
        return StringUtils.nvl(getConfig("boz.version"),"1.0.0");
    }

    /**
     * 获取文件上传/下载路径
     * @return
     */
    public static String getProfile(){
        return getConfig("boz.profile");
    }

}
