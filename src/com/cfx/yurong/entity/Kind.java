package com.cfx.yurong.entity;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/17 15:02
 **/
public enum Kind {
    WHITE("白"), GRAY("灰");

    private String mKind;

    Kind() {
    }

    Kind(String kind) {
        mKind = kind;
    }

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }
}
