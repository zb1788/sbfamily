package com.boz;

import com.boz.annotation.log.Log;
import com.boz.annotation.log.constant.enums.BusinessType;
import com.boz.config.ServerConfig;
import com.boz.excel.Customer;
import com.boz.excel.CustomerMapper;
import com.boz.utils.ExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicDemoApplicationTests {

    @Autowired
    private ServerConfig serverConfig;


    @Autowired
    private CustomerMapper customerMapper;


    @Test
    public void contextLoads() {

        String url = serverConfig.getUrl();
        System.out.println(url);
    }


    @Test
    @Log(title = "dsfaa",businessType = BusinessType.INSERT)
    public void test(){
        System.out.println("fasfa");
    }

    @Test
    public void tt(){
        List<Customer> customers = customerMapper.selectList(null);
        customers.forEach(System.out::println);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        util.exportExcel(customers,"用户表");
    }

    @Test
    public void importa(){
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);




        InputStream is = null;
        try {
            String url = "D:/profile/d0a17b5d-a1b2-49aa-912c-c971b9e8d687_用户表.xlsx";
            String url1 = "D:/profile/a.txt";
            is = new FileInputStream(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




        try {
            List<Customer> customers = util.importExcel(is);
            customers.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }





}
