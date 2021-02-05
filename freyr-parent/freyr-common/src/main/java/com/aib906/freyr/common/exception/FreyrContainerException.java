package com.aib906.freyr.common.exception;

/**
 * @Author: zjs
 * @Date: 2021/1/30 7:46 下午
 */
public class FreyrContainerException extends RuntimeException {

    public FreyrContainerException(String message) {
        super(message);
    }

    public FreyrContainerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FreyrContainerException(Throwable cause) {
        super(cause);
    }
}
