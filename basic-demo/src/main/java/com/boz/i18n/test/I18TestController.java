package com.boz.i18n.test;

import com.boz.i18n.utils.LocalMessage;
import com.boz.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author boz
 * @date 2019/6/4
 */

@RestController
@RequestMapping("/i18test")
public class I18TestController {



    @Autowired
    LocalMessage localMessage;



    @RequestMapping("/getMessage")
    public JSONResult getMessage(){
        String msg = localMessage.getMessage("user.not.exists");
        return JSONResult.ok(msg);
    }



    /**
     * 切换语言
     * @param request
     * @param lang
     * @return
     */
    @RequestMapping("/changeSessionLanguage")
    public JSONResult changeSessionLanguage(HttpServletRequest request, @RequestParam("lang") String lang){
        String country = null;
        if("zh".equals(lang)){
            country = "CN";
        }else if("en".equals(lang)){
            country = "US";
        }
        if(country != null){
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale(lang,country));
        }

        String msg = localMessage.getMessage("user.not.exists");
        return JSONResult.ok(msg);
    }
}
