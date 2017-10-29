package com.hull.controller;

import com.hull.common.base.BaseController;
import com.hull.common.web.ResponseDTO;
import com.hull.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
public class IndexController extends BaseController {

    @Resource
    private DemoService demoService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public View index(){
        return new RedirectView("/loginView");
    }

    @RequestMapping(value = "/time",method = RequestMethod.GET)
    public String getTime(){
        Date now = demoService.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(now);
        return "当前时间："+time;
    }

    @RequestMapping("/400")
    public ResponseDTO<String> baddRequest() {
        return ResponseDTO.badRequest();
    }

    @RequestMapping("/404")
    public ResponseDTO<String> pageNotFound() {
        return ResponseDTO.pageNotFound();
    }

    @RequestMapping("/403")
    public ResponseDTO<String> forbidden() {
        return ResponseDTO.forbidden();
    }

    @RequestMapping("/405")
    public ResponseDTO<String> methodNotSupported() {
        return ResponseDTO.badRequest();
    }

    @RequestMapping("/500")
    public ResponseDTO<String> error() {
        return ResponseDTO.error();
    }

}
