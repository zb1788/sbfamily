package com.boz.ehcache.service;

import com.boz.ehcache.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author boz
 * @date 2019/8/15
 */
@Service
@Slf4j
public class UserService {


    @Cacheable(value = "userCache")
    public User selectUserById(int id){
        System.out.println("id:" + id);

        User user = new User();

        user.setId(id);
        user.setAge(id);
        user.setName("name_" + id);

        return user;

    }



    @CacheEvict(value = "userCache",key = "#id")
    public void cacheClearById(int id){
        log.info("清除id为{}的缓存",id);
    }



    /**
     * 清除缓存
     */
    @CacheEvict(value = "userCache",allEntries = true)
    public void cacheClear(){
        System.out.println("清除缓存");
    }

}
