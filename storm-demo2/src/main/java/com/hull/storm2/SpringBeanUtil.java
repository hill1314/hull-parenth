package com.hull.storm2;

import org.springframework.context.ApplicationContext;

/**
 * 直接通过Spring 上下文获取SpringBean,Spring容器启动后需要注入ApplicationContext
 *
 * @author hull
 * @create 2017-10-28 上午11:49
 * @desc
 **/
public class SpringBeanUtil {

    private static ApplicationContext context;

    public static void setContext(ApplicationContext contextinject) {
        if (context != null) {
            return;
        }
        context = contextinject;
    }

    public static Object getBeanByName(String beanName){
        return context.getBean(beanName);
    }

    public static <T> T getBeanByType(Class<T> type){
        return context.getBean(type);
    }

}
