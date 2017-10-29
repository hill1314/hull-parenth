package com.hull.common.web;

import com.hull.common.base.SerializableEntity;
import com.hull.common.enums.RespStatusEnum;

/**
 * 接口调用返回对象的包装类
 *
 * @author hull
 * @create 2017-10-27 下午9:08
 * @desc
 **/
public class ResponseDTO<T> extends SerializableEntity{
    /**
     * 返回状态码
     */
    private int status;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 只允许通过静态方法创建对象
     */
    private ResponseDTO() {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功
     * @param <T>
     * @return
     */
    public static <T> ResponseDTO<T> success(){
        return success(RespStatusEnum.SUCCESS.getMsg(),null);
    }

    public static <T> ResponseDTO<T> success(T data){
        return success(RespStatusEnum.SUCCESS.getMsg(),data);
    }

    public static <T> ResponseDTO<T> success(String msg){
        return success(msg,null);
    }
    public static <T> ResponseDTO<T> success(String msg,T data){
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.status = RespStatusEnum.SUCCESS.getStatus();
        responseDTO.msg = msg;
        responseDTO.data = data;
        return responseDTO;
    }

    /**
     * 失败
     * @param <T>
     * @return
     */
    public static <T> ResponseDTO<T> fail(){
        return fail(RespStatusEnum.FAIL.getMsg(),null);
    }

    public static <T> ResponseDTO<T> fail(T data){
        return fail(RespStatusEnum.FAIL.getMsg(),data);
    }

    public static <T> ResponseDTO<T> fail(String msg){
        return fail(msg,null);
    }

    public static <T> ResponseDTO<T> fail(String msg,T data){
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.status = RespStatusEnum.FAIL.getStatus();
        responseDTO.msg = msg;
        responseDTO.data = data;
        return responseDTO;
    }

    /**
     * 其他
     * @param status
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDTO<T> error(int status,String msg,T data){
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.status = status;
        responseDTO.msg = msg;
        responseDTO.data = data;
        return responseDTO;
    }

    public static <T> ResponseDTO<T> error(){
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.status = RespStatusEnum.SERVER_ERROR.getStatus();
        responseDTO.msg = RespStatusEnum.SERVER_ERROR.getMsg();
        return responseDTO;
    }

    public static <T> ResponseDTO<T> forbidden() {
        ResponseDTO<T> resp = new ResponseDTO<T>();
        resp.status = RespStatusEnum.FORBIDDEN.getStatus();
        resp.msg = RespStatusEnum.FORBIDDEN.getMsg();
        return resp;

    }

    public static <T> ResponseDTO<T> badRequest() {
        return badRequest(RespStatusEnum.BAD_REQUEST.getMsg(), null);
    }

    public static <T> ResponseDTO<T> badRequest(String msg) {
        return badRequest(msg, null);
    }

    public static <T> ResponseDTO<T> badRequest(String msg, T data) {
        ResponseDTO<T> resp = new ResponseDTO<T>();
        resp.data = data;
        resp.status = RespStatusEnum.BAD_REQUEST.getStatus();
        resp.msg = msg;
        return resp;
    }

    public static <T> ResponseDTO<T> pageNotFound() {
        ResponseDTO<T> resp = new ResponseDTO();
        resp.status = RespStatusEnum.PAGE_NOT_FOUND.getStatus();
        resp.msg = RespStatusEnum.PAGE_NOT_FOUND.getMsg();
        return resp;
    }
}
