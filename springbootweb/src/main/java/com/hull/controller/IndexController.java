package com.hull.controller;

import com.hull.common.base.BaseController;
import com.hull.common.utils.MD5Util;
import com.hull.common.web.ResponseDTO;
import com.hull.entity.db.SysUser;
import com.hull.entity.dto.SysUserDTO;
import com.hull.service.DemoService;
import com.hull.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public View index(){
        return new RedirectView("/loginView");
    }

    @RequestMapping(value = "/loginView", method = RequestMethod.GET)
    public ModelAndView loginView(){
        return view("login");
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.GET)
    public ResponseDTO<SysUserDTO> doLogin(HttpServletRequest request, @RequestBody SysUserDTO user){
        //判空
        if (user == null) {
            return ResponseDTO.fail("用户名和密码不能为空");
        }
        String userName = user.getUserName();
        String password = user.getPassword();
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return ResponseDTO.fail("用户名和密码不能为空");
        }

        //验证码
        HttpSession session = request.getSession();
        String yzm = user.getRandcode();
        if(StringUtils.isEmpty(yzm)){
            return ResponseDTO.fail("请输入验证码");
        }else {
            String randCode = (String) session.getAttribute("randomCode");
            if (!yzm.equals(randCode)) {
                return ResponseDTO.fail("验证码输入有误,请刷新后重新输入!");
            }
        }

        //密码验证
        SysUser sysUser = loginService.getSysUserByUserName(user.getUserName());
        if (sysUser == null) {
            return ResponseDTO.fail("此用户无效");
        }else{
            if(!StringUtils.equals(sysUser.getPassword(),MD5Util.encrypt(password))){
                return ResponseDTO.fail("用户名或者密码错误");
            }
            session.setAttribute("user", sysUser);
            return ResponseDTO.success(user);
        }
    }




    @RequestMapping(value = "/time",method = RequestMethod.GET)
    public String getTime(){
        Date now = demoService.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(now);
        return "当前时间："+time;
    }
}
