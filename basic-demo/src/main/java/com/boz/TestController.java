package com.boz;

import com.boz.utils.JSONResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimerTask;

/**
 * @author boz
 * @date 2019/7/24
 */

@RequestMapping("testcon")
@RestController
public class TestController {

    private static String ABC = "123";

    @RequestMapping("test")
    public JSONResult test(){

//        int i =1;
//        String a = String.valueOf(i);
//        System.out.println(Md5Utils.hash("qjhemail"));
//        System.out.println(StringUtils.getFileName("c:/a/b/c/tts.txt", "/"));
//
//        System.out.println(StringUtils.getSeparatorBefore("abc.pic", "."));
//        System.out.println(StringUtils.getSeparatorAfter("abc.pic", "."));
//

        return JSONResult.ok();
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

}
