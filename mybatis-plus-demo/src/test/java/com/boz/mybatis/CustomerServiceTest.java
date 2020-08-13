package com.boz.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boz.mybatis.generator.entity.Customer;
import com.boz.mybatis.generator.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author boz
 * @date 2019/7/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    /**
     * 查询所有记录
     */
    @Test
    public void listAll(){
        List<Customer> customerList = customerService.list();
        customerList.forEach(System.out::println);
    }

    @Test
    public void listByIds(){
        Collection<Customer> customerList = customerService.listByIds(Arrays.asList(1, 2, 3));
        customerList.forEach(System.out::println);
    }

    @Test
    public void listMaps(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.gt("id",2);
        List<Map<String, Object>> maps = customerService.listMaps(customerQueryWrapper);
        maps.forEach(System.out::println);

    }

    @Test
    public void listByPage(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.gt("id",2);

        Page<Customer> objectPage = new Page<>(1,2);

        objectPage.setSearchCount(true);//是否显示总条数
        IPage<Customer> page = customerService.page(objectPage, customerQueryWrapper);

        System.out.println("总条数"+page.getTotal());
        System.out.println("总页数"+page.getPages());
        System.out.println("数据："+page.getRecords());
        System.out.println("当前页码："+page.getCurrent());
        System.out.println("每页数量"+page.getSize());
    }


    @Test
    public void listByPageMap(){
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.gt("id",2);

        Page<Customer> objectPage = new Page<>(1,2);


        IPage<Map<String, Object>> page = customerService.pageMaps(objectPage, customerQueryWrapper);

        System.out.println("总条数"+page.getTotal());
        System.out.println("总页数"+page.getPages());
        System.out.println("数据："+page.getRecords());
        System.out.println("当前页码："+page.getCurrent());
        System.out.println("每页数量"+page.getSize());
    }


    @Test
    public void test(){
        List<Customer> list = new ArrayList<>();
        for(int i= 120001;i<10000000;i++){
            Customer customer = new Customer(String.valueOf(i),i);
            list.add(customer);
            if(i%10000 == 0){
                if(list.size()>0){
                    customerService.saveBatch(list);
                    list.clear();
                }
            }
        }
    }

}
