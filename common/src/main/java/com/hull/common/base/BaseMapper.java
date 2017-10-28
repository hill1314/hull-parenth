package com.hull.common.base;

/**
 * 所有mapper 接口的基类
 *
 * @author hull
 * @create 2017-10-28 上午10:50
 * @desc
 **/
public interface BaseMapper<PK, T> {

    int add(T vo);

    int delete(PK pk);

    int updateIgnoreNull(T vo);

    int update(T vo);

    T get(PK pk);
}
