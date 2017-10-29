package com.hull.controller;

import com.hull.common.base.BaseController;
import com.hull.common.utils.BeanCopyUtil;
import com.hull.common.utils.MD5Util;
import com.hull.common.web.ResponseDTO;
import com.hull.entity.db.SysUser;
import com.hull.entity.dto.SysUserDTO;
import com.hull.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录相关
 *
 * @author hull
 * @create 2017-10-29 上午11:29
 * @desc
 **/
@RestController
public class LoginController extends BaseController{

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/loginView", method = RequestMethod.GET)
    public ModelAndView loginView(){
        return view("login/login");
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        return view("login/home");
    }

    @RequestMapping(value = "/login")
    public ResponseDTO<SysUserDTO> doLogin(HttpServletRequest request
            , @RequestParam String userName, @RequestParam String password){
        SysUserDTO user = new SysUserDTO();
        user.setUserName(userName);
        user.setPassword(password);
        return doLogin(request,user);
    }
    @RequestMapping(value = "/login2")
    public ResponseDTO<SysUserDTO> doLogin(HttpServletRequest request, @RequestBody SysUserDTO userDto){
        //判空
        if (userDto == null) {
            return ResponseDTO.fail("用户名和密码不能为空");
        }
        String userName = userDto.getUserName();
        String password = userDto.getPassword();
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return ResponseDTO.fail("用户名和密码不能为空");
        }

        //验证码
        HttpSession session = request.getSession();
//        String yzm = user.getRandcode();
//        if(StringUtils.isEmpty(yzm)){
//            return ResponseDTO.fail("请输入验证码");
//        }else {
//            String randCode = (String) session.getAttribute("randomCode");
//            if (!yzm.equals(randCode)) {
//                return ResponseDTO.fail("验证码输入有误,请刷新后重新输入!");
//            }
//        }

        //密码验证
        SysUser sysUser = loginService.getSysUserByUserName(userDto.getUserName());
        if (sysUser == null) {
            return ResponseDTO.fail("此用户未注册");
        }else{
            if(!StringUtils.equals(sysUser.getPassword(), MD5Util.encrypt(password))){
                return ResponseDTO.fail("用户名或者密码错误");
            }
            BeanCopyUtil.copy(sysUser,userDto);
            session.setAttribute("user", userDto);
            return ResponseDTO.success(userDto);
        }
    }
}
