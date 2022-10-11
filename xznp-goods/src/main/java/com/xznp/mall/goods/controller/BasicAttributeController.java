package com.xznp.mall.goods.controller;


import com.xznp.mall.controller.CurdController;
import com.xznp.mall.goods.entity.BasicAttribute;
import com.xznp.mall.goods.mapper.BasicAttributeMapper;
import com.xznp.mall.util.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-22
*/
@RestController
@RequestMapping("/goods/attribute")
public class BasicAttributeController extends CurdController<BasicAttribute> {


    @Resource
    BasicAttributeMapper basicAttributeMapper;

    /** @Author zyz
     * @Description 逻辑删除一个商品属性
     * @Date 13:53 2022/9/22
     * @Param
     * @return
     **/
    @PostMapping("/deleteAttributeById")
    public Result deleteAttributeById(@RequestBody BasicAttribute basicAttribute) {
        //将属性的状态设为D就是逻辑删除
       basicAttribute.setStatus("D");
        int affectRows = basicAttributeMapper.updateById(basicAttribute);
        if (affectRows > 0) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("删除失败");
        }
    }


}
