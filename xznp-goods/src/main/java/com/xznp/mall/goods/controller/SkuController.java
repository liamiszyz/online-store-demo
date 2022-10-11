package com.xznp.mall.goods.controller;


import com.xznp.mall.controller.CurdController;
import com.xznp.mall.goods.entity.Sku;
import com.xznp.mall.goods.mapper.SkuMapper;
import com.xznp.mall.util.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-28
 */
@RestController
@RequestMapping("/goods/sku")
public class SkuController extends CurdController {



    @Resource
    SkuMapper skuMapper;

    /** @Author zyz
     * @Description 逻辑删除一个Sku
     * @Date 09:35 2022/9/29
     * @Param [sku]
     * @return com.xznp.mall.util.result.Result
     **/
    @PostMapping("/del")
    public Result deleteSku(@RequestBody Sku sku){

        //设置状态为D(已删除)
        sku.setStatus("D");
        int update = skuMapper.updateById(sku);
        //判断返回值
        if(update==1){
            return Result.ok().message("删除成功！");
        } else {
            return Result.error().message("删除成功！");
        }
    }
}
