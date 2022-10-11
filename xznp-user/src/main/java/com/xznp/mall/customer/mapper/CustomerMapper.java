package com.xznp.mall.customer.mapper;

import com.xznp.mall.customer.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2022-09-23
 */
@Primary
public interface CustomerMapper extends BaseMapper<Customer> {

}
