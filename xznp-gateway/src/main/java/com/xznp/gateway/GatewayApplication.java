package com.xznp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@SpringBootApplication
@EnableDiscoveryClient
@Controller
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "This is Gateway Application," +
				"hello, now is: " + new Date().toString();
	}

}
