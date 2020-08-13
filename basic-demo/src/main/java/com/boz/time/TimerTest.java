package com.boz.time;

import com.boz.manager.AsyncManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author boz
 * @date 2019/8/1
 */
public class TimerTest {
    public static void main(String[] args) {


        AsyncManager.me().execute(task());









//
//
//        Timer timer = new Timer();
//        System.out.println("schedule task");
//        new Reminder(timer,3);
//        System.out.println("Task schedule");
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        timer.cancel();
    }


    public static TimerTask task(){
        return new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("task .............");
            }
        };
    }


    public static class Reminder{


        public Reminder(Timer timer, int sec){
           timer.schedule(new TimerTask() {
               @Override
               public void run() {
                   System.out.println("Timer's up");
//                   timer.cancel();
               }
           },sec,3000);
        }
    }


}
