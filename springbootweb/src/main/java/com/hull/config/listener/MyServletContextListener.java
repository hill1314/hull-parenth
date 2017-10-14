package com.hull.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author leilei.hu
 * @create 2017-10-14 下午10:36
 * @desc
 **/
@WebListener
public class MyServletContextListener implements ServletContextListener{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //只在服务启动时 初始化 ，执行一次
        logger.info("MyServletContextListener init ... ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //只在服务停止时  执行一次
        logger.info("MyServletContextListener destroy ... ");

    }
}
