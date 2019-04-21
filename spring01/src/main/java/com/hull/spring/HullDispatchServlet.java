package com.hull.spring;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * spring 核心类
 *
 * @author
 * @create 2018-11-11 下午10:30
 **/

public class HullDispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();
    private Map<String,Object> beanMap = new ConcurrentHashMap<>();
    private List<String> classNames = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("============= do post method =============");

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("=======init===========");

        //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //加载
        doDefinitionParse(contextConfig.getProperty("package"));

        //注册
        doRegister();

        //自动依赖注入
        doAutowried();

        //mvc 中会有这一步，将 requestMapping中配的url 和 具体的处理方关联
        //根据浏览器输入的url ，通过反射去调用
        initHandleMapper();

    }



    private void doLoadConfig(String location) {
        System.out.println("=======doLoadConfig===========");
        InputStream is = this.getClass().getResourceAsStream(location.replace("classpath:",""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void doDefinitionParse(String packageName) {
        //将类路径 转为包路径
        URL url = this.getClass().getClassLoader().getResource(
                "/" + packageName.replaceAll("\\.","/"));

        File classDir = new File(url.getFile());

        //遍历包下的所有文件
        for (File file : classDir.listFiles()){
            if(file.isDirectory()){
                doDefinitionParse(packageName + "." +file.getName());
            }else {
                classNames.add(packageName + "." + file.getName().replace(".class",""));
            }
        }
    }

    private void doRegister() {

    }

    private void doAutowried() {
        
    }

    private void initHandleMapper() {
    }

    /**
     * 首字母转为小写
     * @param str
     * @return
     */
    private String lowerFirstCase(String str){
        char [] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    
}
