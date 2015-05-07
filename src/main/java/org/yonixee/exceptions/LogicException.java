package org.yonixee.exceptions;

/**
 * Created by yonixee on 15/5/7.
 */
public class LogicException extends Exception {

    /**
     * 错误码
     */
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LogicException() {
        super();
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, int code) {
        super(message);
    }
}