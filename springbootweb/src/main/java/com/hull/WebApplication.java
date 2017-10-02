package com.hull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author leilei.hu
 * @create 2017-09-24 下午5:24
 * @desc
 **/

@ComponentScan(basePackages = { "com.hull" })
@EnableAutoConfiguration
public class WebApplication {
    static Logger logger = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
        init(); //初始化
    }

    private static void init() {
        logger.info("spring-boot-web start ...");
    }
}
