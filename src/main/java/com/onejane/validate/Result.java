package com.onejane.validate;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/7/31.
 */
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    /**
     * 成功的时候调用
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> tResult = new Result<>();
        tResult.setCode(ResultEnum.SUCCESS.getCode());
        tResult.setMsg(ResultEnum.SUCCESS.getMsg());
        tResult.setData(data);
        return tResult;
    }

    /**
     * 失败的时候调用
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(ResultEnum resultEnum) {
        Result<T> tResult = new Result<>();
        tResult.setCode(resultEnum.getCode());
        tResult.setMsg(resultEnum.getMsg());
        return tResult;
    }

    public Result() {

    }

    public Result(ResultEnum resultEnum) {
        if (resultEnum != null) {
            this.code = resultEnum.getCode();
            this.msg = resultEnum.getMsg();
        }
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
