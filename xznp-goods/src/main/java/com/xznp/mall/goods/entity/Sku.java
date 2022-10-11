package com.xznp.mall.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyz
 * @since 2022-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Sku implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private LocalDateTime createTime;

    private Long creator;

    private Long operator;

    private String status;

    private LocalDateTime updateTime;

    private String allowNoStockSale;

    private Long brandId;

    private Long categoryId;

    private String hasStock;

    private Long imageId;

    private Long modelId;

    private String remark;

    private String showAgreement;

    private String skuCode;

    private String skuName;

    private Long sort;

    /**
     * 最低购买数量
     */
    private Integer minNum;

    /**
     * 重量（克）
     */
    private Integer heft;

    /**
     * 采购价（紧用于计算快递免邮数量）
     */
    private String purchasePrice;

    /**
     * 购买价（紧用于计算快递免邮数量）
     */
    private String originalPrice;

    /**
     * 保质期
     */
    private String warrantyTime;

    /**
     * 积分
     */
    private Integer integral;

    /**
     * 供应商库存
     */
    private Integer gysStock;

    /**
     * 仓库库存
     */
    private Integer ckStock;

    /**
     * 供应商
     */
    private Integer distributorId;

    /**
     * 收货地址编号
     */
    private String addressCode;

    /**
     * 系列号
     */
    private String seriesNo;

    private Integer parentId;

    /**
     * 免运费金额
     */
    private Integer minAmount;

    /**
     * 库存警戒数量
     */
    private Integer warningCount;

    /**
     * 库存保有量
     */
    private Integer keepCount;

    /**
     * 发货方式
     */
    private String distributorType;

    /**
     * 单位
     */
    private String skuUnit;

    /**
     * 是否使用积分
     */
    private String isUsedIntegral;

    /**
     * 是否免邮产品 0否 1是
     */
    private String freeShipping;

    /**
     * 产品目录别名
     */
    private String productName;


}
