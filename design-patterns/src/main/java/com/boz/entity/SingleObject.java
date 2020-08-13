package com.boz.entity;

/**
 * 单例模式
 * @author boz
 * @date 2019/8/19
 */
public class SingleObject {

    private static SingleObject instance = new SingleObject();

    private SingleObject(){}

    public static SingleObject getInstance(){
        return instance;
    }
}
