package com.wys.library.bean;

import org.jetbrains.annotations.NotNull;

/**
 * 数据格式响应
 * @param <T>
 */
public class ComResponse<T> {
    /**
     * 不同的后段返回的响应字段可能不同 ，根据情况不同改变字段
     */
    private String errorMsg;
    private int    errorCode;
    private T      data;

    public String getMessage() {
        return errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public T getData() {
        return data;
    }

    // 根据不同的定义定义不同的成功状态
    public boolean success() {
        return errorCode == 0;
    }

    @NotNull
    @Override
    public String toString() {
        return "CommonResponse{" + "message='" + errorMsg + '\'' + ", errorCode=" + errorCode + ", data=" + data + '}';
    }
}
