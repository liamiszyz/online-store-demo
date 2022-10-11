package com.xznp.mall.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品类目归属表
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("goods_category_rela")
public class CategoryRela implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类ID
     */
    @TableField("CATEGORY_ID")
    private Integer categoryId;

    /**
     * 归属值
     */
    @TableField("RELA_VAL")
    private String relaVal;

    /**
     * 状态 'E'可用 'D'不可用
     */
    @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField("CREATOR")
    private Integer creator;

    /**
     * 操作人
     */
    @TableField("OPERATOR")
    private Integer operator;


}
