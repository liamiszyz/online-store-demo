package com.xznp.mall.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class JWTUtils {
    /**
     * 生成Token
     */
    private static final String  SING="NISHIDACONGMING123";

    public static String createToken(Map<String,String> map) throws UnsupportedEncodingException {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        //创建JWT builder
        JWTCreator.Builder builder= JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String   token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
        return token;
    }

    /**
     * 验证token 合法性
     */
    public static  DecodedJWT verify(String token) throws UnsupportedEncodingException {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /**
     * 获取token信息
     * @param token
     * @return
     */
    public static Map<String,String> getTokenInfo(String token) throws UnsupportedEncodingException {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String exp= sdf1.format(verify.getExpiresAt());
        Map<String, Claim> claims = verify.getClaims();
        Map<String,String> m=new HashMap<>();
        claims.forEach((k,v)->{
            m.put(k,v.asString());
        });
        m.put("exp",exp);
        return m;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String sing="JINWANDALAOHU";
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,20);
        String token = JWT.create()
                .withClaim("username", "xxx")
                .withClaim("userid", "1")
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(sing));

        System.out.println(token);

        System.out.println("---------------------开始解析------------");
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String exp= sdf1.format(verify.getExpiresAt());
        Map<String, Claim> claims = verify.getClaims();
        Map<String,String> m=new HashMap<>();
        claims.forEach((k,v)->{
            m.put(k,v.asString());
            m.remove("exp");
        });
        m.put("exp",exp);
        m.forEach((k,v)->{
            System.out.println("k"+k+"-----"+v);
        });

        System.out.println("verify"+verify.getClaims());
        System.out.println(verify.getClaim("username").asString());
        System.out.println(verify.getClaim("userid").asLong());
        System.out.println(verify.getExpiresAt());


    }
}

