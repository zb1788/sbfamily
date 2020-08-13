package com.boz.excel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.boz.annotation.excel.Excel;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author boz
 * @since 2019-07-09
 */
public class Customer implements Serializable {

    public Customer(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Customer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Customer() {
    }

    private static final long serialVersionUID=1L;

    @Excel(name = "用户序号", prompt = "用户编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "用户名称")
    private String name;

    @Excel(name = "用户年龄")
    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
        "id=" + id +
        ", name=" + name +
        ", age=" + age +
        "}";
    }
}
