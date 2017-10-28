package com.hull.common.base;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author leilei.hu
 * @create 2017-09-24 下午5:49
 * @desc
 **/
public class BaseController extends Base{

    public ModelAndView view(String view) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(view);
        return mv;
    }

    public ModelAndView view(String view, Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(view);
        mv.addAllObjects(model.asMap());
        return mv;
    }
}
