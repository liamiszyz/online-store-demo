package com.xznp.mall.goods.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xznp.mall.goods.entity.Category;
import com.xznp.mall.goods.entity.CategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
 */

@Service
public interface ICategoryService extends IService<Category> {
List<CategoryVO> selectList();
}
