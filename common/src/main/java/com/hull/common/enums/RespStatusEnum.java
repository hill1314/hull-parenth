package com.hull.common.enums;

/**
 * TODO 来点注释
 *
 * @author hull
 * @create 2017-10-27 下午9:14
 * @desc
 **/
public enum RespStatusEnum {
    SUCCESS(0, "success"),
    FAIL(1, "error"),
    BAD_REQUEST(400, "参数异常"),
    FORBIDDEN(403, "没有访问权限"),
    PAGE_NOT_FOUND(404, "页面不存在"),
    SERVER_ERROR(500, "服务器开小差"),
    NEED_LOGIN(600, "请先登录");

    private int status;
    private String msg;

    private RespStatusEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
