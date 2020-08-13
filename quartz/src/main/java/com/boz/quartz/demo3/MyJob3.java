package com.boz.quartz.demo3;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author boz
 * @date 2019/8/19
 */
@Component
public class MyJob3 {

    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //通过crond设置
    @Scheduled(cron = "30-40 * * * * ?")
    public void reportCurrentTime(){
        System.out.println("MyJob3 时间：" + dataFormat.format(new Date()));
    }

    //每个3秒执行一次
    @Scheduled(fixedRate = 3000)
    public void rep1(){
        System.out.println("MyJob3 3秒执行一次时间：" + dataFormat.format(new Date()));
    }


}
