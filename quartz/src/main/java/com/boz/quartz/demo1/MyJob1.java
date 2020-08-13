package com.boz.quartz.demo1;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author boz
 * @date 2019/8/19
 */
@Component
public class MyJob1 {
    public void sayHello(){
        System.out.println("MyJob1>>>" + new Date());
    }
}
