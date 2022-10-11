package com.xznp.mall.staff.entity;

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
 * 人员表
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ts_staff_info")
public class TsStaffInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 人员ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录账号
     */
    @TableField("STAFF_CODE")
    private String staffCode;

    /**
     * 人员名称
     */
    @TableField("STAFF_NAME")
    private String staffName;

    /**
     * 密码
     */
    @TableField("PASSWD")
    private String passwd;

    /**
     * 联系电话
     */
    @TableField("CONTACT_TEL")
    private String contactTel;

    /**
     * 性别 1 男 0 女
     */
    @TableField("SEX")
    private Integer sex;

    /**
     * 状态:E在用  D删除
     */
    @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 操作人员
     */
    @TableField("OPERATOR")
    private Integer operator;

    /**
     * 创建者
     */
    @TableField("CREATOR")
    private Integer creator;

    /**
     * 邮箱地址
     */
    @TableField("EMAIL")
    private String email;

    private Long attchid;

    private String staffType;

    /**
     * 同步ID
     */
    @TableField("SYN_ID")
    private String synId;

    /**
     * 职务
     */
    private String duty;

    private String issupermanager;

    /**
     * 身份证
     */
    @TableField("CER_NO")
    private String cerNo;

    /**
     * 省份编码
     */
    @TableField("PROVINCE_CODE")
    private String provinceCode;

    /**
     * 地市编码
     */
    @TableField("CITY_CODE")
    private String cityCode;

    /**
     * 县市编码
     */
    @TableField("AREA_CODE")
    private String areaCode;


}
