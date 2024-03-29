package com.cfx.yurong.utils;


/**
 * Project: Ajax
 * Create By: Chen.F.X
 * DateTime: 2024/1/7 11:01
 **/
public class Logit {
    public static void d(String tag, String msg) {
        System.out.println(tag + " " + msg);
    }

    public static void d(String tag, String msg, Throwable throwable) {
        System.out.println(tag + " " + msg + "\n" + throwable.getMessage());
    }
}
