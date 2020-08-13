package com.boz;

import com.boz.config.ServerConfig;
import com.boz.file.configread.yml.entity.Project;
import com.boz.utils.JSONResult;
import com.boz.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author boz
 * @date 2019/7/10
 */

@RestController
@RequestMapping("/sys")
public class SysController extends BaseController{

    @Autowired
    private Project project;

    @Autowired
    private ServerConfig serverConfig;

    @GetMapping("/test")
    public JSONResult testServletUtil(){
        String name = ServletUtils.getParameter("name");
        return JSONResult.ok(name);
    }

    @GetMapping("/testDomain")
    public JSONResult testdomain(){
        String url = serverConfig.getUrl();

        System.out.println(project.getProfile());

        System.out.println(request);
        System.out.println(getPath());

//        String downloadPath = new Global().getDownloadPath();
//        System.out.println(downloadPath);
        return JSONResult.ok(url);
    }


}
