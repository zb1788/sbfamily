package com.boz;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void test(){
        String test = "<a href=\"/nba/2019/0826-548ab55-svideo.htm\" target=\"_blank\">波波示意大洛上场 洛佩兹拼命挥手拒绝</a> | 约旦男篮vs中国国奥男篮 <a href=\"/nba/2019/0826-08261d26e3a-luxiang.htm\" target=\"_blank\">录像</a> | 约旦男篮vs中国国奥男篮 ";
        String pattern = "\\|(.*?)录像";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(test);
        System.out.println(m.find());
        System.out.println(m.group(0));
    }
}
