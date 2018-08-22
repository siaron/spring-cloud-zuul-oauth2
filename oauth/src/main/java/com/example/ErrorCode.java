package com.example;

public enum ErrorCode {

    BAD_REQUEST(400, "user not allow"),
    UNAUTHORIZED(401, "user not allow"),
    FORBIDDEN(403, "user not allow"),
    METHOD_NOT_ALLOWED(405, "user not allow"),
    INTERNAL_SERVER_ERROR(500, "user not allow"),
    BOOK_STATUS_WRONG(1100, "user not allow"),
    BOOK_OWNERSHIP_WRONG(1101, "user not allow"),
    NO_TOKEN(1102, "user not allow"),
    NOT_FOUND(404, "user not allow"),
    DEVICE_NOT_FOUND(1103, "user not allow");

    public int httpStatus;
    public String message;

    ErrorCode(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
