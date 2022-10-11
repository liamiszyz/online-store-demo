package com.xznp.mall.goods.entity;

import lombok.Data;

/**
 * @description:基本型号类的VO类
 * @author: zyz
 * @time: 2022/9/27 13:39
 */

@Data
public class BaiscModelVO {
    Integer id;
    String modelName;
    String modelCode;
    String brandName;
    String categoryName;
}
