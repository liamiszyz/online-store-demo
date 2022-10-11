package com.xznp.mall.goods.mapper;

import com.xznp.mall.goods.entity.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2022-09-28
 */
public interface SkuMapper extends BaseMapper<Sku> {



    @Select("SELECT\n" +
            "\tsku.id,\n" +
            "\tsku.sku_code,\n" +
            "\tsku.sku_name,\n" +
            "\tsku.model_id,\n" +
            "\tbasic_model.model_name,\n" +
            "\tsku.allow_no_stock_sale,\n" +
            "\tsku.category_id,\n" +
            "\tgoods_category.category_name\n" +
            "FROM\n" +
            "\tsku\n" +
            "\tLEFT JOIN basic_model ON basic_model.id = sku.model_id\n" +
            "\tLEFT JOIN goods_category ON goods_category.id = sku.category_id\n" +
            "where sku.status='E'")
    List<Sku> selectList();
}
