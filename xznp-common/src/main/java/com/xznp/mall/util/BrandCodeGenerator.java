package com.xznp.mall.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateFormatUtils;


import java.util.Date;
import java.util.Random;

public class BrandCodeGenerator {

    /** @Author zyz
     * @Description 商品编码生成器，使用时间编码 不是极限情况不会重复
     * @Date 16:49 2022/9/26
     * @Param [brandName]
     * @return java.lang.String
     **/
    public static String generateBrandCode(String brandName) {

        String code = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        Random re = new Random();
        //生成一个随机四位数
        String random = String.valueOf(re.nextInt(9999 - 1000 + 1) + 1000);
        String ordernumber = code + random;

        //MD5 加密 时间加上 xxxx字段
        String codeMD5 = DigestUtils.md5Hex(code + ordernumber + brandName);
        return "pp" + codeMD5;
    }

    public static void main(String[] args) {
        System.out.println(generateBrandCode("你好"));
    }



}
