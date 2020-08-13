package com.boz.quartz.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author boz
 * @date 2019/8/16
 */
@Service
public class HelloService {
    public void sayHello(){
        System.out.println("hello service >>>" + new Date());
    }
}