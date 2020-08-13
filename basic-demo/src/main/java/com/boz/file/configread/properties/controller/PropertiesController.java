package com.boz.file.configread.properties.controller;

import com.boz.utils.JSONResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author boz
 * @date 2019/7/10
 */


/**
 * 单个读取properties的内容
 * 步骤：1 @PropertySource(value = {"classpath:author.properties"},encoding = "utf-8")
 *      2 @Value("${author.name}")
 */
@RestController
@RequestMapping("properties")
@PropertySource(value = {"classpath:author.properties"},encoding = "utf-8")
public class PropertiesController {

    @Value("${author.name}")
    String name;


    @GetMapping("name")
    public JSONResult getValue(){
        return JSONResult.ok(name);
    }
}
