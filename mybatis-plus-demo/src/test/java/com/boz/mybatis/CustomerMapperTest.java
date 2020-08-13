package com.boz.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boz.mybatis.generator.entity.Customer;
import com.boz.mybatis.generator.mapper.CustomerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author boz
 * @date 2019/7/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerMapperTest {

    @Autowired
    CustomerMapper customerMapper;


    //查询所有数据
    @Test
    public void seletAllTest(){
        List<Customer> customers = customerMapper.selectList(null);
        customers.forEach(System.out::println);
    }

    //通过id查询单条数据
    @Test
    public void selectByIdTest(){
        Customer customer = customerMapper.selectById(1);
        System.out.println(customer);
    }

    //通过多个id查询多条数据
    @Test
    public void  selectByIdsTest(){
        List<Customer> customers = customerMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        customers.forEach(System.out::println);
    }

    //查询数量
    @Test
    public void selectByCountTest(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.gt("age",20);
        Integer integer = customerMapper.selectCount(customerQueryWrapper);
        System.out.println(integer);
    }

    @Test
    public void selectWhereTest1(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.gt("age",20).like("name","b");
        List<Customer> customers = customerMapper.selectList(customerQueryWrapper);
        customers.forEach(System.out::println);
    }


    @Test
    public void selectWhereTest2(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.gt("age",20).or().like("name","b");
        List<Customer> customers = customerMapper.selectList(customerQueryWrapper);
        customers.forEach(System.out::println);
    }


    @Test
    public void selectWhereTest3(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.gt("age",20).or().like("name","b");
        List<Customer> customers = customerMapper.selectList(customerQueryWrapper);
        customers.forEach(System.out::println);
    }


    @Test
    public void selectWhereTest4(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.and(i -> i.likeRight("name","c").gt("age",20)).or(i -> i.eq("age",34));
        List<Customer> customers = customerMapper.selectList(customerQueryWrapper);
        customers.forEach(System.out::println);
    }


    @Test
    public void selectByMap(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.gt("age",20).select("name","age");
        List<Map<String, Object>> maps = customerMapper.selectMaps(customerQueryWrapper);
        maps.forEach(System.out::println);
    }


    /**
     * 分页查询只包含当前页的内容
     */
    @Test
    public void selectByPage1(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.gt("age",10);

        Page<Customer> customerPage = new Page<Customer>(1,2);
        customerPage.setSearchCount(false);//是否显示总条数
        IPage<Customer> customerIPage = customerMapper.selectPage(customerPage, customerQueryWrapper);

        System.out.println("总条数"+customerIPage.getTotal());
        System.out.println("总页数"+customerIPage.getPages());
        System.out.println("数据："+customerIPage.getRecords());
        System.out.println("当前页码："+customerIPage.getCurrent());
        System.out.println("每页数量"+customerIPage.getSize());



    }

    /**
     * 分页查询，包含总条数，总页数信息
     */
    @Test
    public void selectByPage2(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.gt("age",10);

        Page<Customer> customerPage = new Page<Customer>(1,2);
        customerPage.setSearchCount(true);//是否显示总条数
        IPage<Map<String, Object>> mapIPage = customerMapper.selectMapsPage(customerPage, customerQueryWrapper);


        System.out.println("总条数"+mapIPage.getTotal());
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("数据："+mapIPage.getRecords());
        System.out.println("当前页码："+mapIPage.getCurrent());
        System.out.println("每页数量"+mapIPage.getSize());



    }
}
