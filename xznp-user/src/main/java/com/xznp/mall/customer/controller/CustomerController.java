package com.xznp.mall.customer.controller;


import com.xznp.mall.controller.CurdController;
import com.xznp.mall.customer.entity.Customer;
import com.xznp.mall.customer.mapper.CustomerMapper;
import com.xznp.mall.util.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-23
 */
@RestController
@RequestMapping("/customer")
public class CustomerController extends CurdController {

    @Resource
    CustomerMapper customerMapper;


    /** @Author zyz
     * @Description 逻辑删除一个顾客
     * @Date 10:33 2022/9/30
     * @Param [customer]
     * @return com.xznp.mall.util.result.Result
     **/
    @PostMapping("/del")
    public Result deleteCustomerByIdLogicly(@RequestBody Customer customer) {

        //设置状态为D（已删除）
        customer.setStatus("D");
        int i = customerMapper.updateById(customer);
        if (i > 0) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("删除失败");
        }
    }



}
