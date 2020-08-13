package com.boz.ehcache.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author boz
 * @date 2019/8/15
 */
@Data
public class User implements Serializable {

    private Integer id;
    private String name;
    private Integer age;

}
