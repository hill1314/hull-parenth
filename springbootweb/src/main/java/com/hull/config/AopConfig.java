package com.hull.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author leilei.hu
 * @create 2017-10-15 上午11:45
 * @desc
 **/
@Configuration //标识为配置类
@ComponentScan("com.hull.config.aspect")
@EnableAspectJAutoProxy //开启spring 对aop的支持
public class AopConfig {

}
