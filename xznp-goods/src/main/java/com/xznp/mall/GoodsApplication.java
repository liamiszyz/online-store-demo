package com.xznp.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller

@EnableDiscoveryClient
@SpringBootApplication

@MapperScan("com.xznp.mall.staff.mapper")
@MapperScan("com.xznp.mall.goods.mapper")
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "This is Good Application," +
                "hello, now is: " + new Date().toString();
    }

//    @RequestMapping("/generator")
//    @ResponseBody
//    public String generator() {
//        CodeGenerator.main(null);
//        return "hello, now is: " + new Date().toString();
//    }

}
