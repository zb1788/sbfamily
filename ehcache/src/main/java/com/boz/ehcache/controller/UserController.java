package com.boz.ehcache.controller;

import com.boz.ehcache.entity.User;
import com.boz.ehcache.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author boz
 * @date 2019/8/15
 */

@RestController
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("index/{id}")
    @ApiOperation("根据用户ID获取用户信息,有缓存就从缓存中读取")
    public String getUser(@PathVariable int id){

        User user = userService.selectUserById(id);
        System.out.println(user);
        return "ok";
    }

    @GetMapping("clear/{id}")
    @ApiOperation("清理某个ID的缓存")
    public String clearId(@PathVariable int id){
        userService.cacheClearById(id);
        return "clear";
    }

    @GetMapping("clear")
    @ApiOperation("清理所有的缓存")
    public String cacheClear(){
        userService.cacheClear();
        return "clear";
    }

}
