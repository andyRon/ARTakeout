package com.andyron.takeout.common;

/**
 * 自定义业务异常类
 * @author andyron
 **/
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
