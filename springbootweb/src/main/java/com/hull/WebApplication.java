package com.hull;

import com.hull.config.MybatisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author leilei.hu
 * @create 2017-09-24 下午5:24
 * @desc
 **/
@Configuration
@Import({MybatisConfig.class})
@ComponentScan(basePackages = { "com.hull" })
@ServletComponentScan  //使 filter 和 listener 生效
@EnableAutoConfiguration
public class WebApplication {
    static Logger logger = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
        init(); //初始化
    }

    private static void init() {
        logger.info("spring-boot-web started !!!");
    }
}
