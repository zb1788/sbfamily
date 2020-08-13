package com.boz.file.configread.yml.controller;

import com.boz.config.Global;
import com.boz.file.configread.yml.entity.Project;
import com.boz.utils.JSONResult;
import com.boz.utils.YamlUtil;
import com.boz.utils.text.StrFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boz
 * @date 2019/7/10
 */

@RestController
@RequestMapping("yml")
public class YmlController {

    //1获取单个yml中的属性的方法，直接@Value获取
    @Value("${boz.name}")
    String projectName;

    @Value("${boz.copyrightYear}")
    String projectVersion;

    //:#{null}作用为在取不到对应配置值时，采用默认值null赋值
    @Value("${boz.projectInfo:#{null}}")
    String projectInfo;

    @Autowired
    private Project project;


    String separator=System.getProperty("line.separator");

    //获取单个yml中的属性
    @GetMapping("getYmlValue")
    public JSONResult getYmlValue(){
        String str = StrFormatter.format("projectName:{}; projectVersion:{};  projectInfo:{}",projectName,projectVersion,projectInfo);
        return JSONResult.ok(str);
    }


    /**
     * 批量获取属性
     * 1.新建对应的entity（Project）
     * 2.新建对应的config（ProjectConfig）
     * 3.自动注入entity（Project）
     * @return
     */
    @GetMapping("getAllYmlValue")
    public JSONResult getAllYmlValue(){
        return JSONResult.ok(project.toString());
    }


    /**
     * 通过工具类读取yml文件
     * @return
     */
    @GetMapping("readYmlByTools")
    public JSONResult readYmlByTools(){
        Map<?, ?> yamlMap = null;
        try {
            yamlMap = YamlUtil.loadYaml("application.yml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return JSONResult.ok(yamlMap);
    }

    @GetMapping("writeYmlByTools")
    public JSONResult writeYmlByTools(){
        Map<?, ?> yamlMap = null;
        try {
            yamlMap = YamlUtil.loadYaml("application.yml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //自定义yml的内容
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        HashMap map = new HashMap<String,String>();
        map.put("name","s");
        map.put("path","dd");
        objectObjectHashMap.put("boz",map);


        try {
            YamlUtil.dumpYaml(Global.getProfile()+"/1/2/test.yml",yamlMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONResult.ok();
    }

}
