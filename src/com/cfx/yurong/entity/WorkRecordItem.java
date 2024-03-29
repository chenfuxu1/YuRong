package com.cfx.yurong.entity;

import com.cfx.yurong.utils.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/22 23:32
 * <p>
 * 保存每个拆羽绒的所有账单信息
 **/
public class WorkRecordItem {
    private String mWorkName;
    private List<YuRongItem> mYuRongItems = new ArrayList<>(); // 所有的账单记录信息集合
    private BigDecimal mTotalWhiteHeight; // 白绒总重量
    private BigDecimal mTotalGrayHeight; // 灰绒总重量
    private BigDecimal mTotalHeight; // 总重量
    private BigDecimal mTotalMoney; // 总金额

    public WorkRecordItem() {
    }

    public String getWorkName() {
        return mWorkName;
    }

    public void setWorkName(String workName) {
        mWorkName = workName;
    }

    public List<YuRongItem> getYuRongItems() {
        return mYuRongItems;
    }

    public void setYuRongItems(List<YuRongItem> yuRongItems) {
        mYuRongItems.clear();
        mYuRongItems.addAll(yuRongItems);

        BigDecimal tempTotalWhiteHeight = new BigDecimal(0);
        BigDecimal tempTotalGrayHeight = new BigDecimal(0);
        BigDecimal tempTotalHeight = new BigDecimal(0);
        for (YuRongItem yuRongItem : mYuRongItems) {
            if (yuRongItem != null) {
                String kind = yuRongItem.getKind();
                BigDecimal height = yuRongItem.getHeight();
                if (Kind.WHITE.getKind().equals(kind)) {
                    tempTotalWhiteHeight = tempTotalWhiteHeight.add(height);
                } else if (Kind.GRAY.getKind().equals(kind)) {
                    tempTotalGrayHeight = tempTotalGrayHeight.add(height);
                }
                tempTotalHeight = tempTotalHeight.add(height);
            }
        }
        setTotalWhiteHeight(tempTotalWhiteHeight);
        setTotalGrayHeight(tempTotalGrayHeight);
        setTotalHeight(tempTotalHeight);
        setTotalMoney(tempTotalHeight.multiply(BigDecimal.valueOf(Constants.YU_RONG_PRICE)));
    }

    public BigDecimal getTotalWhiteHeight() {
        return mTotalWhiteHeight;
    }

    public void setTotalWhiteHeight(BigDecimal totalWhiteHeight) {
        mTotalWhiteHeight = totalWhiteHeight;
        if (mTotalWhiteHeight != null) {
            mTotalWhiteHeight = mTotalWhiteHeight.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getTotalGrayHeight() {
        return mTotalGrayHeight;
    }

    public void setTotalGrayHeight(BigDecimal totalGrayHeight) {
        mTotalGrayHeight = totalGrayHeight;
        if (mTotalGrayHeight != null) {
            mTotalGrayHeight = mTotalGrayHeight.setScale(1, BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getTotalHeight() {
        return mTotalHeight;
    }

    public void setTotalHeight(BigDecimal totalHeight) {
        mTotalHeight = totalHeight;
        if (mTotalHeight != null) {
            mTotalHeight = mTotalHeight.setScale(1, BigDecimal.ROUND_HALF_UP);
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
