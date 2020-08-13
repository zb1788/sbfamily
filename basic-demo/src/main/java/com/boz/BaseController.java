package com.boz;

import com.boz.file.configread.yml.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author boz
 * @date 2019/7/11
 */

public class BaseController {


    @Autowired
    private Project project;


    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;



    public String getPath(){
        return project.getProfile();
    }
}
