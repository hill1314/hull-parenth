package com.hull.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hull.common.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leilei.hu
 * @create 2017-10-14 下午7:34
 * @desc
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("login interceptor preHandle ...");

        long startTime = System.currentTimeMillis();
        request.setAttribute("methodCallStartTime", Long.valueOf(startTime));
        String url = "";
        Object params = null;
        HashMap logInfoMap = new HashMap();

        try {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            url = this.getUrl(handlerMethod);
            params = getRequestMethodParams(request, handlerMethod);
            logInfoMap.put("url", url);
            logInfoMap.put("uid", WebUtil.getUid());
            logInfoMap.put("startTime", Long.valueOf(startTime));
            logInfoMap.put("params", params);
        } catch (Exception e) {
            logInfoMap.put("status", 500);
            logInfoMap.put("msg", e.getMessage());
            logger.error("Request preHandle: url = {} --> {}",
                    new Object[]{url, JSON.toJSONString(logInfoMap,
                            new SerializerFeature[]{SerializerFeature.WriteMapNullValue}), e});
        } finally {
            logInfoMap.remove("params");
            request.setAttribute("methodCallInfoMap", logInfoMap);
        }

        return true;
    }

    /**
     * 获取请求的url
     * @param handlerMethod
     * @return
     */
    private String getUrl(HandlerMethod handlerMethod) {
        String mappingUrl = "";
        String classUrl = "";
        String methodUrl = "";
        RequestMapping classMapping = (RequestMapping)handlerMethod.getBean().getClass().getAnnotation(RequestMapping.class);
        RequestMapping methodMapping = (RequestMapping)handlerMethod.getMethodAnnotation(RequestMapping.class);
        if(classMapping != null && classMapping.value().length > 0) {
            classUrl = classMapping.value()[0] == null?"":classMapping.value()[0];
        }

        if(methodMapping != null && methodMapping.value() != null && methodMapping.value().length != 0) {
            methodUrl = methodMapping.value()[0] == null?"":methodMapping.value()[0];
        }

        mappingUrl = classUrl + methodUrl;
        return mappingUrl;
    }

    /**
     * 获取请求的参数
     * @param request
     * @param handlerMethod
     * @return
     */
    private static Map<String, Object> getRequestMethodParams(HttpServletRequest request, HandlerMethod handlerMethod) {
        Map<String, Object> paramMap = new HashMap();
        Map pathVariables = (Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        List<Map<String, Object>> formParameters = new ArrayList();
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();

        paramMap.put("pathVariables", pathVariables);
        paramMap.put("formParameters", formParameters);
        paramMap.put("methodParameters", methodParameters);
        return paramMap;
    }


    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        logger.info("login interceptor postHandle ...");

        long endTime = System.currentTimeMillis();
        long beginTime = (long) request.getAttribute("methodCallStartTime");
        HashMap logInfoMap = (HashMap) request.getAttribute("methodCallInfoMap");
        long excutetime = endTime - beginTime;

        logger.info(logInfoMap.get("url") + " : execute time : " + excutetime + "ms");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("login interceptor afterCompletion ...");

    }

    @Override
    public void afterConcurrentHandlingStarted(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("login interceptor afterConcurrentHandlingStarted ...");

    }
}
