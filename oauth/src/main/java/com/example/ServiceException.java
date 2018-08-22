package com.example;

/**
 * 自定义异常
 * @author fxp
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -8634700792767837033L;

    public ErrorCode errorCode;

    public ServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
