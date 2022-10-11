package com.xznp.mall.util;

import org.apache.commons.codec.binary.Base64;


import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: RSA加密算法的工具类
 * @author: zyz
 * @time: 2022/9/26 10:47
 */


public class RSAUtil {


    public static Map<String, String> createKeyPair(int keySize) {

        //为RSA算法创建keyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such Algorithm!");
        }
        //初始化keyPairGenerator，设置长度
        kpg.initialize(keySize);
        //生成密钥对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        //将生成的密钥对存储到map中
        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

}
