package com.hull;

//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @author
 * @create 2018-01-19 下午6:13
 **/

//@SpringBootApplication
//@EnableTransactionManagement
//@ComponentScan("com.hull")
//@PropertySource(value = { "classpath:application.properties","classpath:storm.properties"})
//public class SpringBootstrap extends SpringBootServletInitializer {
public class SpringBootstrap {

    /**
     * 设置 安全线程launcher实例
     **/
    private volatile static SpringBootstrap springBootstrap;

    /**
     * 设置上下文
     **/
    private ApplicationContext context;


//    public static void main(String[] args) {
//        SpringApplicationBuilder application = new SpringApplicationBuilder(SpringBootstrap.class);
//        application.run(args);
//        SpringBootstrap bootstrap = new SpringBootstrap();
//        bootstrap.setApplicationContext(application.context());
//        setStormLauncher(bootstrap);
//    }

    private static void setStormLauncher(SpringBootstrap SpringBootstrap) {
        SpringBootstrap.springBootstrap = SpringBootstrap;
    }

//    public static SpringBootstrap getStormLauncher() {
//        return springBootstrap;
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(SpringBootstrap.class);
//    }


    /**
     * 获取上下文
     *
     * @return the application context
     */
    public ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 设置上下文.
     *
     * @param appContext 上下文
     */
    private void setApplicationContext(ApplicationContext appContext) {
        this.context = appContext;
    }

    /**
     * 通过自定义name获取 实例 Bean.
     *
     * @param name the name
     * @return the bean
     */
    public Object getBean(String name) {
        return context.getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param <T>   the type parameter
     * @param clazz the clazz
     * @return the bean
     */
    public <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param <T>   the type parameter
     * @param name  the name
     * @param clazz the clazz
     * @return the bean
     */
    public <T> T getBean(String name, Class<T> clazz) {
        return context.getBean(name, clazz);
    }
}
