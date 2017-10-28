package com.hull.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 *  所有实体类的基类
 *  (1 实现序列化；2 重写toString 方法)
 *
 * @author hull
 * @create 2017-10-28 上午10:42
 * @desc
 **/
public class BaseEntity implements Serializable{

    @Override
    public String toString() {
        try {
            return this.getClass().getName() + " = "
                    + JSON.toJSONString(this,
                    new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
        } catch (Exception var2) {
            return super.toString();
        }
    }
}
