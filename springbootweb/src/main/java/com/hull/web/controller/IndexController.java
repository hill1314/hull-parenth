package com.hull.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leilei.hu
 * @create 2017-09-30 上午10:58
 * @desc
 **/
@RestController
public class IndexController extends BaseController{

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        logger.info("spring-boot-web start!");
        return "hello ,spring-boot-web start!";
    }
}
