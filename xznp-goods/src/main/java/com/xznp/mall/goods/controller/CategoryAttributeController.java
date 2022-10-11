package com.xznp.mall.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.xznp.mall.controller.CurdController;
import com.xznp.mall.goods.entity.BasicAttribute;
import com.xznp.mall.goods.entity.Category;
import com.xznp.mall.goods.entity.CategoryAttribute;
import com.xznp.mall.goods.mapper.BasicAttributeMapper;
import com.xznp.mall.goods.mapper.CategoryAttributeMapper;
import com.xznp.mall.goods.mapper.CategoryMapper;
import com.xznp.mall.util.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-22
 */
@RestController
@RequestMapping("/goods/category-attribute")
public class CategoryAttributeController extends CurdController<CategoryAttribute> {

    @Resource
    CategoryAttributeMapper categoryAttributeMapper;
    @Resource
    CategoryMapper categoryMapper;

    @Resource
    BasicAttributeMapper basicAttributeMapper;

    /**
     * @return
     * @Author zyz
     * @Description 通过属性id获取分类名称列表
     * 整体思路：通过属性id获取属性详情，通过分类id列表获取分类名称列表
     * @Date 11:28 2022/9/22
     * @Param
     **/
    @PostMapping("/listCategoryByAttributeId")
    public Result listCategoryByAttributeId(@RequestBody CategoryAttribute categoryAttribute) {

        //条件构造器
        QueryWrapper<CategoryAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attribute_id", categoryAttribute.getAttributeId());
        //通过attribute_id 查询到分类属性的列表
        List<CategoryAttribute> categoryAttributes = categoryAttributeMapper.selectList(queryWrapper);
        List<String> categoryNameList = new ArrayList<>();
        BasicAttribute attribute = null;

         // 遍历列表 通过id查询名名称
        for (int i = 0; i < categoryAttributes.size(); i++) {
            Long id = categoryAttributes.get(i).getCategoryId();
            QueryWrapper queryWrapperCategory = new QueryWrapper();
            QueryWrapper queryWrapperAttribute = new QueryWrapper();
            queryWrapperCategory.eq("id", id);
            queryWrapperAttribute.eq("id", categoryAttribute.getAttributeId());
            //查询attribute
            attribute = basicAttributeMapper.selectOne(queryWrapperAttribute);
            //查询category,判空
            Category category = categoryMapper.selectOne(queryWrapperCategory);
            if (category != null) {
                categoryNameList.add(category.getCategoryName());
            }

        }
        return Result.ok()
                .data("attribute", attribute)
                .data("categoryNameList", categoryNameList);
    }


}
