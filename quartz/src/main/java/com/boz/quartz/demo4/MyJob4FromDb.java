package com.boz.quartz.demo4;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author boz
 * @date 2019/8/19
 */
@Component
public class MyJob4FromDb implements SchedulingConfigurer {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Mapper
    public interface CronMapper{

        @Select("select cron from cron limit 1")
        String getCron();
    }


    @Autowired
    @SuppressWarnings("all")
    CronMapper cronMapper;



    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容（Runnable）
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("执行从数据库读取定时任务" + dateFormat.format(new Date()));
                    }
                },
                //设置执行周期(Trigger)
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        String cron = cronMapper.getCron();
                        return new CronTrigger(cron).nextExecutionTime(triggerContext);
                    }
                }
        );

    }
}
