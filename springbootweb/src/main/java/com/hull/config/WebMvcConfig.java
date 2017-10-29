package com.hull.config;

import com.hull.config.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author leilei.hu
 * @create 2017-10-10 下午10:59
 * @desc
 **/
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final String[] RESOURCE_LOCATIONS =
            {"classpath:/resources/", "classpath:/static/"};
    private static final String[] LOGIN_EXCLUED_LOCATIONS = {
            "classpath:/static/",
            "/",
            "/forgot_password",
            "/change_password",
            "/sign_up",
            "/tool/*",
            "/register",
            "/home",
            "/login",
            "/logout",
            "/alive",
            "/error",
            "/404",
            "/403",
            "/500"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(RESOURCE_LOCATIONS);
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(LOGIN_EXCLUED_LOCATIONS);
        super.addInterceptors(registry);
    }
}
