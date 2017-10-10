package com.hull.mapper;

import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author leilei.hu
 * @create 2017-09-30 下午4:52
 * @desc
 **/
@Repository
public interface DemoMapper {
    Date getTime();
}
