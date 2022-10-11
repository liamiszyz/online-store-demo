package com.xznp.mall.customer.service.impl;

import com.xznp.mall.customer.entity.Customer;
import com.xznp.mall.customer.mapper.CustomerMapper;
import com.xznp.mall.customer.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyz
 * @since 2022-09-23
 */
@Service

public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
