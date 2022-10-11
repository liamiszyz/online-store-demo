package com.xznp.mall.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private LocalDateTime createTime;

    private Long creator;

    private Long operator;

    private String status;

    private LocalDateTime updateTime;

    private String areaCode;

    private String auditStatus;

    private Double bargainPrice;

    private Long categoryId;

    private String cityCode;

    private String goodsCode;

    private Long goodsImageId;

    private String goodsTitle;

    /**
     * 商品分类(乡总农品、乡总优选)
     */
    private String goodsType;

    private String isForSale;

    private String markType;

    private Long partnerId;

    private Double planPrice;

    private String priceUnit;

    private String provinceCode;

    private String remark;

    private Long sort;

    private Long merchantId;

    /**
     * 经销类型
     */
    private String distributorType;

    /**
     * 经销，供应商Id
     */
    private Integer distributorId;

    /**
     * 商品类型1乡总农品2乡总优选
     */
    private Integer prodType;

    /**
     * 是否非卖品（0否1是）
     */
    private Integer isFmp;

    /**
     * 活动开始时间
     */
    @TableField("ACTIVITY_BEGIN_TIME")
    private LocalDateTime activityBeginTime;

    /**
     * 活动结束时间
     */
    @TableField("ACTIVITY_END_TIME")
    private LocalDateTime activityEndTime;

    /**
     * 小程序详情图
     */
    @TableField("DETAIL_IMG_ID")
    private Long detailImgId;


}
