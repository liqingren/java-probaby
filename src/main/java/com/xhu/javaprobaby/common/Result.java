package com.xhu.javaprobaby.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg = "成功";
    private T data;

    public Result(){
        super();
    }

    public Result(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功不带数据
     * @return
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 成功带数据
     * @param object 数据
     * @return
     */
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.success.getCode());
        result.setMsg(ResultEnum.success.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 成功带提示
     * @param msg 提示
     * @return
     */
    public static Result success(String msg){
        Result result = new Result();
        result.setCode(ResultEnum.success.getCode());
        result.setMsg(msg);
        return result;
    }

    /**
     * 成功带提示及数据
     * @param object 数据
     * @return
     */
    public static Result success(String msg, Object object){
        Result result = new Result();
        result.setCode(ResultEnum.success.getCode());
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    /**
     * 失败
     * @param msg 提示信息
     * @return
     */
    public static Result fail(String msg){
        Result result = new Result();
        result.setCode(ResultEnum.failed.getCode());
        result.setMsg(msg);
        return result;
    }

    /**
     * 错误
     * @param msg 提示信息
     * @return
     */
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(ResultEnum.error.getCode());
        result.setMsg(msg);
        return result;
    }

}
