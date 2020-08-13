package com.boz.controller;


import com.boz.annotation.DataSource;
import com.boz.config.datasource.DynamicDataSourceContextHolder;
import com.boz.entity.Customer;
import com.boz.enums.DataSourceType;
import com.boz.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @author boz
 * @date 2019/8/6
 */
@RestController
@RequestMapping("datasource")
public class CustomerController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CustomerMapper customerMapper;




    @GetMapping("slave")
    @DataSource(value = DataSourceType.SLAVE)
    public String tt(){

        System.out.println("doing1111...............");

        List<Customer> customers = customerMapper.selectList(null);
        customers.forEach(System.out::println);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from customer");
        maps.forEach(System.out::println);
        DynamicDataSourceContextHolder.clearDataSourceType();

        return "ok";
    }


    @GetMapping("master")
    @DataSource(value = DataSourceType.MASTER)
    public String tta(){


        List<Customer> customers = customerMapper.selectList(null);
        customers.forEach(System.out::println);
        System.out.println("doing2222...............");

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from customer");
        maps.forEach(System.out::println);

        return "ok";
    }



}
