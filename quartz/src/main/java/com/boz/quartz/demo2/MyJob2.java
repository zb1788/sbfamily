package com.boz.quartz.demo2;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.Map;

/**
 * @author boz
 * @date 2019/8/19
 */
//实现Job接口的任务，默认是无状态的，若要将Job设置成有状态的，在quartz中是给实现的Job添加@DisallowConcurrentExecution注解
public class MyJob2 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        for(Map.Entry entry : jobDataMap.entrySet()){
            System.out.println("key---:" + entry.getKey() + "|value---" + entry.getValue());
        }
        System.out.println("MyJob2>>>" + new Date());
    }
}
