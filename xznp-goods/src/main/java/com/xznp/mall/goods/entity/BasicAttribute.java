package com.xznp.mall.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BasicAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private LocalDateTime createTime;

    private Long creator;

    private Long operator;

    private String status;

    private LocalDateTime updateTime;

    private String attributeAlias;

    private String attributeName;

    private String isFilter;

    private String isSku;

    private String remark;

    private String skuOrder;

    private Long sort;

    private String type;


}
