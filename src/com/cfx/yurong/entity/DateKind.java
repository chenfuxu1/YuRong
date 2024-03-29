package com.cfx.yurong.entity;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/17 15:05
 **/
public enum DateKind {
    ALL(0), AM(1), PM(2);

    private Integer mKind;

    DateKind() {
    }

    DateKind(Integer kind) {
        mKind = kind;
    }

    public Integer getKind() {
        return mKind;
    }

    public void setKind(Integer kind) {
        mKind = kind;
    }
}
