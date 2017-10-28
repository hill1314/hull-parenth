package com.hull.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基类，所有 controller、service、comment 都需要继承的类
 * （包含公共的实现，暂时只有logger）
 *
 * @author hull
 * @create 2017-10-27 下午8:13
 * @desc
 **/
public class Base {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

}
