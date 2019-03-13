package com.wys.library.bean;

import org.jetbrains.annotations.NotNull;

public class CommonResponse<T> {
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
