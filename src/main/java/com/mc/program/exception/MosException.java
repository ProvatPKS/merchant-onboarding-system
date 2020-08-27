package com.mc.program.exception;

import com.mc.program.vo.ResponseEnum;

public class MosException extends Exception {

    private String errorCode;

    public MosException(String message) {
        super(message);
    }

    public MosException(String message, Throwable cause) {
        super(message, cause);
    }

    public MosException(String message, Throwable cause, ResponseEnum responseEnum) {
        super(message, cause);
        this.errorCode = responseEnum.getCode();
    }

    public MosException(String message, ResponseEnum responseEnum) {
        super(message);
        this.errorCode = responseEnum.getCode();
    }

    public String getErrorCode() {
        return errorCode;
    }


}
