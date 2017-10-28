package com.hull.config.listener;

import com.hull.common.base.Base;
import com.hull.common.utils.SpringBeanUtil;
import com.hull.common.utils.WebUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author leilei.hu
 * @create 2017-10-14 下午10:36
 * @desc
 **/
@WebListener
public class MyServletContextListener extends Base implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        //只在服务启动时 初始化 ，执行
        try {
            logger.info("####################### MyServletContextListener Initializing... ####################################################");
            ServletContext servletContext = contextEvent.getServletContext();
            ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(contextEvent.getServletContext());
            WebUtil.APP_ROOT_PATH = servletContext.getRealPath("/");
            logger.info("setting app_root_path = {}", WebUtil.APP_ROOT_PATH);
            SpringBeanUtil.setContext(applicationContext);
            logger.info("inject applicationContext into  SpringBeanUtil");
            logger.info("####################### MyServletContextListener Initializing DONE... ############################################");
        } catch (Exception e) {
            logger.error("AppInitializeListener Initializing fail", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //只在服务停止时  执行一次
        logger.info("MyServletContextListener destroy ... ");

    }
}
