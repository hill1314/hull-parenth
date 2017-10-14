package com.hull.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 *
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * @create 2017-10-14 下午10:17
 * @desc
 **/
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //只在服务启动时 初始化 ，执行一次
        logger.info("myFilter init ...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //每次调用服务都会执行
        logger.info("myFilter filter ...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //只在服务停止时  执行一次
        logger.info("myFilter destroy ...");
    }
}
