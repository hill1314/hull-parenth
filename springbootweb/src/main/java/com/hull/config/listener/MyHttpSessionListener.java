package com.hull.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author leilei.hu
 * @create 2017-10-14 下午10:41
 * @desc
 **/
@WebListener
public class MyHttpSessionListener implements HttpSessionListener{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("MyHttpSessionListener init ... ");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("MyHttpSessionListener destroy ... ");

    }
}
