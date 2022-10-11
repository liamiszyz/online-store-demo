package com.xznp.mall.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("goods_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;


    public Category() {
    }

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private LocalDateTime createTime;

    private Long creator;

    private Long operator;

    private String status;

    private LocalDateTime updateTime;

    private Long categoryImageId;

    private String categoryName;

    private String isHot;

    private Long parentId;

    private Long sort;

    /**
     * 层级
     */
    private Integer level;


}
