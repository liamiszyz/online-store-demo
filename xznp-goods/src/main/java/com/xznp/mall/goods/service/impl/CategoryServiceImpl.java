package com.xznp.mall.goods.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xznp.mall.goods.entity.Category;
import com.xznp.mall.goods.entity.CategoryVO;
import com.xznp.mall.goods.mapper.CategoryMapper;
import com.xznp.mall.goods.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> selectList() {
        List<CategoryVO> categoryVOS = categoryMapper.selectCategoryList();
        return categoryVOS;
    }
}
