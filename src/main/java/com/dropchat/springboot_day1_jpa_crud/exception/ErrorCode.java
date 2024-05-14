package com.dropchat.springboot_day1_jpa_crud.exception;

public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(999, "Uncategorized Error"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXISTED(1001, "User existed"),
    USER_NOT_EXISTED(1005, "User existed"),
    UNAUTHENTICATED(1006, "Unauthenticated"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters")
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
