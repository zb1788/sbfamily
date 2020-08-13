package com.boz.mybatis.generator.service.impl;

import com.boz.mybatis.generator.entity.Customer;
import com.boz.mybatis.generator.mapper.CustomerMapper;
import com.boz.mybatis.generator.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author boz
 * @since 2019-07-09
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
