package com.luoguohua.finance.common.domain;

import com.luoguohua.finance.common.exception.BusinessException;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.http.HttpStatus;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/5/11 16:20
 * Content:
 */
public class R<T> implements Serializable {

    /**
     * 错误码
     *
     * @see org.springframework.http.HttpStatus
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示，用户可阅读
     *
     */
    private String msg;



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     *
     * 因为 A 方法返回的 CommonResult 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T> 返回的泛型
     * @return 新的 CommonResult 对象
     */
    public static <T> R<T> error(R<?> result) {
        return error(result.getCode(), result.getMsg());
    }

    public static <T> R<T> error(HttpStatus httpStatus, String message) {
        return error(httpStatus.value(),message);
    }

    public static <T> R<T> error(int code, String message) {
        Assert.isTrue(HttpStatus.OK.value() !=code, "code 必须是错误的！");
        R<T> result = new R<>();
        result.code = code;
        result.msg = message;
        return result;
    }

    public static <T> R<T> error(int code, String message, T data) {
        Assert.isTrue(HttpStatus.OK.value() !=code, "code 必须是错误的！");
        R<T> result = new R<>();
        result.code = code;
        result.msg = message;
        result.data = data;
        return result;
    }

    public static <T> R<T> success(T data) {
        R<T> result = new R<>();
        result.code = HttpStatus.OK.value();
        result.data = data;
        result.msg = "";
        return result;
    }

    public static <T> R<T> success() {
        R<T> result = new R<>();
        result.code = HttpStatus.OK.value();
        result.msg = "操作成功";
        return result;
    }

    public static boolean isSuccess(int code) {
        return Objects.equals(code, HttpStatus.OK.value());
    }

    public static <T> R<T> error(BusinessException serviceException) {
        return error(serviceException.getCode(), serviceException.getMessage());
    }


}