package com.cf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer//配置中心服务
@EnableEurekaClient
@RestController
//@PropertySource("classpath:config/users-dev.properties")
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}

//    @Value("${e.name}")
//    String name;
//    @Value("${e.email}")
//    String email;

//    @RequestMapping("/ne")
//    public String ne(){
//        return name + ":" + email;
//    }
}
