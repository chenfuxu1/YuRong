package com.cfx.yurong.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/23 11:03
 *
 * 风包皮的实体 item
 **/
public class FbpItem {
    private Integer mId; // 风包皮记录 id
    private String mWorkName; // 员工姓名
    private Date mWorkTime; // 风包皮时间
    private Integer mDateKind; // 天数种类 0 - 整天 1 - 上午 2 - 下午
    private BigDecimal mWorkHour; // 工时，整天：1.0 半天：0.5

    public FbpItem() {
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

    public Integer getDateKind() {
        return mDateKind;
    }

    public void setDateKind(Integer dateKind) {
        mDateKind = dateKind;
    }

    public BigDecimal getWorkHour() {
        return mWorkHour;
    }

    public void setWorkHour(BigDecimal workHour) {
        mWorkHour = workHour;
        if (mWorkHour != null) {
            mWorkHour = mWorkHour.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
    }

    @Override
    public String toString() {
        return "FbpItem{" +
                "mId=" + mId +
                ", mWorkName='" + mWorkName + '\'' +
                ", mWorkTime=" + mWorkTime +
                ", mDateKind=" + mDateKind +
                ", mWorkHour=" + mWorkHour +
                '}';
    }
}
