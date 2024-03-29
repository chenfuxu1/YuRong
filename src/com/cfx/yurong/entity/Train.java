package com.cfx.yurong.entity;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/17 15:05
 **/
public enum Train {
    ONE("第一车"), TWO("第二车"), THREE("第三车"), FOUR("第四车"), FIVE("第五车"),
    SIX("第六车"), SEVEN("第七车"), EIGHT("第八车"), NINE("第九车"), TEN("第十车"),
    ELEVEN("第十一车"), TWELVE("第十二车"), THIRTEEN("第十三车"), FOURTEEN("第十四车"),
    FIFTEEN("第十五车"), SIXTEEN("第十六车");

    private String mNumber;

    Train() {
    }

    Train(String number) {
        mNumber = number;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }
}
