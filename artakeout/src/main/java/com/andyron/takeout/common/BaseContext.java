package com.andyron.takeout.common;

/**
 * 基于ThreadLocal封装工具类，用于保持和获取当前登录用户id
 * @author andyron
 **/
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
