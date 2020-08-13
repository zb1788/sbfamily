package com.boz.utils;

import com.boz.utils.text.StringUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 配置处理工具类
 * 
 * @author yml
 */
public class YamlUtil
{
    public static Map<?, ?> loadYaml(String fileName) throws FileNotFoundException
    {
        InputStream in = YamlUtil.class.getClassLoader().getResourceAsStream(fileName);
        return StringUtils.isNotEmpty(fileName) ? (LinkedHashMap<?, ?>) new Yaml().load(in) : null;
    }

    public static void dumpYaml(String filePath, Map<?, ?> map) throws IOException
    {
        File file = new File(File.separator + filePath);

        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        FileWriter fileWriter = new FileWriter(new File(filePath));
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        yaml.dump(map, fileWriter);
    }

    public static Object getProperty(Map<?, ?> map, Object qualifiedKey)
    {
        if (map != null && !map.isEmpty() && qualifiedKey != null)
        {
            String input = String.valueOf(qualifiedKey);
            if (!"".equals(input))
            {
                if (input.contains("."))
                {
                    int index = input.indexOf(".");
                    String left = input.substring(0, index);
                    String right = input.substring(index + 1, input.length());
                    return getProperty((Map<?, ?>) map.get(left), right);
                }
                else if (map.containsKey(input))
                {
                    return map.get(input);
                }
                else
                {
                    return null;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void setProperty(Map<?, ?> map, Object qualifiedKey, Object value)
    {
        if (map != null && !map.isEmpty() && qualifiedKey != null)
        {
            String input = String.valueOf(qualifiedKey);
            if (!input.equals(""))
            {
                if (input.contains("."))
                {
                    int index = input.indexOf(".");
                    String left = input.substring(0, index);
                    String right = input.substring(index + 1, input.length());
                    setProperty((Map<?, ?>) map.get(left), right, value);
                }
                else
                {
                    ((Map<Object, Object>) map).put(qualifiedKey, value);
                }
            }
        }
    }
}