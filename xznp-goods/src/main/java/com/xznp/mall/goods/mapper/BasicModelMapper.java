package com.xznp.mall.goods.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xznp.mall.goods.entity.BaiscModelVO;
import com.xznp.mall.goods.entity.BasicModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2022-09-27
 */
@Primary
public interface BasicModelMapper extends BaseMapper<BasicModel> {



    @Select("SELECT\n" +
            "\tbasic_model.model_name,\n" +
            "\tbasic_model.model_code,\n" +
            "\tbasic_brand.brand_name,\n" +
            "\tgoods_category.category_name \n" +
            "FROM\n" +
            "\tbasic_model\n" +
            "\tLEFT JOIN basic_brand ON basic_brand.id = basic_model.brand_id\n" +
            "\tLEFT JOIN basic_model_category ON basic_model_category.model_id = basic_model.id\n" +
            "\tLEFT JOIN goods_category ON basic_model_category.category_id = goods_category.id \n" +
            "WHERE\n" +
            "\tbasic_model.`status` = 'E'")
     List<BaiscModelVO> selectAllBasicModel();


    @Select("SELECT\n" +
            "\tbasic_model.id,\n" +
            "\tbasic_model.model_name,\n" +
            "\tbasic_model.model_code,\n" +
            "\tbasic_brand.brand_name,\n" +
            "\tgoods_category.category_name \n" +
            "FROM\n" +
            "\tbasic_model\n" +
            "\tLEFT JOIN basic_brand ON basic_brand.id = basic_model.brand_id\n" +
            "\tLEFT JOIN basic_model_category ON basic_model_category.model_id = basic_model.id\n" +
            "\tLEFT JOIN goods_category ON basic_model_category.category_id = goods_category.id \n"+
            "${ew.customSqlSegment}")
    IPage<BaiscModelVO> selectPageModelList(IPage<BaiscModelVO> page, @Param(Constants.WRAPPER) QueryWrapper<BaiscModelVO> wrapper);



}
