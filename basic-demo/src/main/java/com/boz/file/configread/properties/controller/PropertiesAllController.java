package com.boz.file.configread.properties.controller;

import com.boz.file.configread.properties.entity.Author;
import com.boz.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 批量获取properties的内容
 * 步骤：1. @PropertySource(value = {"classpath:author.properties"},encoding = "utf-8")
 *      2. 创建author对应的实体类
 *      3. @Autowired 注入当前类
 * @author boz
 * @date 2019/7/10
 */

@RestController
@RequestMapping("properties")
@PropertySource(value = {"classpath:author.properties"},encoding = "utf-8")
public class PropertiesAllController {

    @Autowired
    private Author author;


    @GetMapping("all")
    public JSONResult getAll(){
        return JSONResult.ok(author.toString());
    }


}
