package com.cfx.yurong.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/17 14:29
 **/
public class YuRongItem {
    private Integer mId; // 羽绒记录 id
    private String mWorkName; // 员工姓名
    private Date mWorkTime; // 拆羽绒时间
    private BigDecimal mHeight; // 羽绒重量
    private String mKind; // 羽绒种类，白绒 或 灰绒
    private String mTrainNumber; // 羽绒车次

    public YuRongItem() {
    }

    public YuRongItem(Integer id, String workName, Date workTime, BigDecimal height, String kind, String trainNumber) {
        mId = id;
        mWorkName = workName;
        mWorkTime = workTime;
        mHeight = height;
        if (mHeight != null) {
            mHeight = mHeight.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
        mKind = kind;
        mTrainNumber = trainNumber;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getWorkName() {
        return mWorkName;
    }

    public void setWorkName(String workName) {
        mWorkName = workName;
    }

    public Date getWorkTime() {
        return mWorkTime;
    }

    public void setWorkTime(Date workTime) {
        mWorkTime = workTime;
    }

    public BigDecimal getHeight() {
        return mHeight;
    }

    public void setHeight(BigDecimal height) {
        mHeight = height;
        if (mHeight != null) {
            mHeight = mHeight.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
    }

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public String getTrainNumber() {
        return mTrainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        mTrainNumber = trainNumber;
    }

    @Override
    public String toString() {
        return "YuRongItem{" +
                "mId=" + mId +
                ", mWorkName='" + mWorkName + '\'' +
                ", mWorkTime=" + mWorkTime +
                ", mHeight=" + mHeight +
                ", mKind='" + mKind + '\'' +
                ", mTrainNumber='" + mTrainNumber + '\'' +
                '}';
    }
}
