package com.boz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boz.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author boz
 * @date 2019/8/6
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}
