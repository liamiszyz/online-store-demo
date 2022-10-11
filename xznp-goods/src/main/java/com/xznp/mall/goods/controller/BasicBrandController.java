package com.xznp.mall.goods.controller;


import com.xznp.mall.controller.CurdController;
import com.xznp.mall.goods.entity.BasicBrand;
import com.xznp.mall.goods.mapper.BasicBrandMapper;
import com.xznp.mall.util.BrandCodeGenerator;
import com.xznp.mall.util.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/goods/brand")
public class BasicBrandController extends CurdController<BasicBrand> {


    @Resource
    BasicBrandMapper basicBrandMapper;


    /**
     * @return int
     * @Author zyz
     * @Description 逻辑删除一个品牌
     * @Date 09:50 2022/9/22
     * @Param [basicBrand]
     **/
    @PostMapping("/delBrandById")

    public int delBrandById(@RequestBody BasicBrand basicBrand) {
        basicBrand.setStatus("D");
        return basicBrandMapper.updateById(basicBrand);
    }

    /**
     * @return
     * @Author zyz
     * @Description 新增一个品牌
     * @Date 09:50 2022/9/22
     * @Param
     **/
    @PostMapping("/insertNewBrand")
    public Result insertNewBrand(@RequestBody BasicBrand basicBrand) {

        //调用品牌编码的生成器，设置品牌的编码，传入的数据没有品牌编码，且数据库字段不可为空，需要生成
        basicBrand.setBrandCode(BrandCodeGenerator.generateBrandCode(basicBrand.getBrandName()));
        //添加一个品牌
        int affectRows = basicBrandMapper.insert(basicBrand);
        //判断数据库返回，是否添加成功
        if (affectRows > 0) {
            return Result.ok().message("添加成功！");
        } else {
            return Result.error().message("添加失败！");
        }
    }
}
