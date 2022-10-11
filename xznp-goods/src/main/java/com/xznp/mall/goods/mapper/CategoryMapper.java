package com.xznp.mall.goods.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.xznp.mall.goods.entity.Category;
import com.xznp.mall.goods.entity.CategoryVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
 */
public interface CategoryMapper extends BaseMapper<Category> {


    @Select("SELECT\n" +
            "\tgoods_category.id,\n" +
            "\tgoods_category.category_name,\n" +
            "\tgoods_category.category_image_id,\n" +
            "\tts_staff_info.STAFF_NAME AS creator_name,\n" +
            "\tgoods_category.create_time,\n" +
            "\tgoods_category.sort,\n" +
            "\tgoods_category.parent_id \n" +
            "FROM\n" +
            "\tgoods_category\n" +
            "\tLEFT JOIN ts_staff_info ON goods_category.creator = ts_staff_info.id \n" +
            "WHERE\n" +
            "\tgoods_category.`status` = 'E'")

    List<CategoryVO> selectCategoryList();


    @Select("SELECT\n" +
            "\tgoods_category.id,\n" +
            "\tgoods_category.category_name,\n" +
            "\tgoods_category.category_image_id,\n" +
            "\tts_staff_info.STAFF_NAME AS creator_name,\n" +
            "\tgoods_category.create_time,\n" +
            "\tgoods_category.sort \n" +
            "FROM\n" +
            "\tgoods_category\n" +
            "\tLEFT JOIN ts_staff_info ON goods_category.creator = ts_staff_info.id\n" +
            "${ew.customSqlSegment}")
    IPage<CategoryVO> selectPageCategoryList(IPage<Category> page, @Param(Constants.WRAPPER) QueryWrapper<Category> wrapper);
}
