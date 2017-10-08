package com.hull.web.controller;

import com.hull.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leilei.hu
 * @create 2017-09-30 上午10:58
 * @desc
 **/
@RestController
public class IndexController extends BaseController{

    @Resource
    DemoService demoService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public View index(){
        logger.info("spring-boot-web start!");
        return new RedirectView("/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        return view("loginView");
    }

    @RequestMapping(value = "/time",method = RequestMethod.GET)
    public String getTime(){
        Date now = demoService.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(now);
        return "当前时间："+time;
    }
}
