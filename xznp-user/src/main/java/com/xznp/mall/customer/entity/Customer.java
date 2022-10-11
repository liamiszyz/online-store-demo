package com.xznp.mall.customer.entity;

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
 * @since 2022-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 注册帐号
     */
    private String account;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 头像id
     */
    private Integer attachId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 省份编码
     */
    private String provinceCode;

    /**
     * 地区编码
     */
    private String cityCode;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 联系地址
     */
    private String contactAddr;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private Long creator;

    private Long operator;

    private LocalDateTime updateTime;

    private String custNo;

    private Long enterpriseId;

    private String idNo;

    private String openId;

    private Long amount;

    /**
     * 是否测试人员(0正常用户1测试用户4分销用户)
     */
    private Integer isTest;

    /**
     * 积分
     */
    private Integer integral;


}
