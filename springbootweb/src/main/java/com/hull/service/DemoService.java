package com.hull.service;

import com.hull.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author leilei.hu
 * @create 2017-10-02 下午6:34
 * @desc
 **/
@Service
public class DemoService {
    @Resource
    DemoMapper demoMapper;

    public Date getTime(){
        Date now = demoMapper.getTime();
        return now;
    }
}
