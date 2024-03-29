package com.cfx.yurong.entity;

import com.cfx.yurong.utils.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/23 11:59
 *
 * 保存每个风包皮的所有账单信息
 **/
public class FbpRecordItem {
    private String mWorkName;
    private List<FbpItem> mFbpItems = new ArrayList<>(); // 所有的账单记录信息集合
    private BigDecimal mTotalDate; // 总天数
    private BigDecimal mTotalMoney; // 总金额

    public FbpRecordItem() {
    }

    public String getWorkName() {
        return mWorkName;
    }

    public void setWorkName(String workName) {
        mWorkName = workName;
    }

    public List<FbpItem> getFbpItems() {
        return mFbpItems;
    }

    public void setFbpItems(List<FbpItem> fbpItems) {
        mFbpItems.clear();
        mFbpItems.addAll(fbpItems);


        BigDecimal tempTotalDate = new BigDecimal(0);
        for (FbpItem fbpItem : mFbpItems) {
            if (fbpItem != null) {
                BigDecimal workHour = fbpItem.getWorkHour();
                tempTotalDate = tempTotalDate.add(workHour);
            }
        }
        setTotalDate(tempTotalDate);
        setTotalMoney(tempTotalDate.multiply(BigDecimal.valueOf(Constants.FBP_PRICE)));

    }

    public BigDecimal getTotalDate() {
        return mTotalDate;
    }

    public void setTotalDate(BigDecimal totalDate) {
        mTotalDate = totalDate;
        if (mTotalDate != null) {
            mTotalDate = mTotalDate.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getTotalMoney() {
        return mTotalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        mTotalMoney = totalMoney;
        if (mTotalMoney != null) {
            mTotalMoney = mTotalMoney.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
    }
}
