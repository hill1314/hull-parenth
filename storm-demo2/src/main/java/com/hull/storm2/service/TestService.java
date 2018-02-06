package com.hull.storm2.service;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2018-01-22 下午1:33
 **/

@Service
public class TestService {
//    Logger logger = LoggerFactory.getLogger(getClass());

    public void print(String msg){
        System.out.println("================"+msg);
    }

}
