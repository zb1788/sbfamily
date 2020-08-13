package com.boz.task;

import com.boz.utils.DateUtils;
import com.boz.utils.text.StrFormatter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author boz
 * @date 2019/8/5
 */
@Component
public class TaskTest {

    @Scheduled(cron = "10-20 * * * * ?")
    public void task1() throws InterruptedException {
        System.out.println(StrFormatter.format("task111--start--我的线程id==>{}----现在时间：{}",Thread.currentThread().getId(), DateUtils.getTime()));
        Thread.sleep(10000);
        System.out.println(StrFormatter.format("task111--end--我的线程id==>{}----现在时间：{}",Thread.currentThread().getId(), DateUtils.getTime()));
    }

    @Scheduled(cron = "10-20 * * * * ?")
    public void task2() throws InterruptedException {
        System.out.println(StrFormatter.format("task2222--start--我的线程id==>{}----现在时间：{}",Thread.currentThread().getId(), DateUtils.getTime()));
        Thread.sleep(2000);
        System.out.println(StrFormatter.format("task2222--end--我的线程id==>{}----现在时间：{}",Thread.currentThread().getId(), DateUtils.getTime()));
    }
}
