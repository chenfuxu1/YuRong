package com.cfx.yurong.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/24 22:52
 *
 * 所有的羽绒记录实体 bean
 **/
public class AllYuRongRecordItem {
    private final List<String> mAllTrains = new ArrayList<>(); // 保存所有的车次名称记录
    private final Map<String, List<String>> mWorkNameByTrainMap = new HashMap<>();
    /**
     * 保存每一车，每一个员工的白灰绒记录
     * key1: 车次
     * key2: 员工名
     * key3：白 / 灰
     */
    private final Map<String, Map<String, Map<String, BigDecimal>>> mEachTrainWorkRecordMap = new HashMap<>();

    public AllYuRongRecordItem() {
    }

    public List<String> getAllTrains() {
        return mAllTrains;
    }

    public void setAllTrains(List<String> allTrains) {
        mAllTrains.clear();
        mAllTrains.addAll(allTrains);
    }

    public Map<String, List<String>> getWorkNameByTrainMap() {
        return mWorkNameByTrainMap;
    }

    public void setWorkNameByTrainMap(Map<String, List<String>> workNameByTrainMap) {
        mWorkNameByTrainMap.clear();
        mWorkNameByTrainMap.putAll(workNameByTrainMap);
    }

    public String getWorkNameSizeStr(String train) {
        List<String> workNameList = mWorkNameByTrainMap.get(train);
        int size = workNameList.size() / 2;
        return String.valueOf(size);
    }

    // 获取所有的行数
    public int getAllRows() {
        int row = 0;
        for (Map.Entry<String, List<String>> entry : mWorkNameByTrainMap.entrySet()) {
            List<String> value = entry.getValue();
            if (value != null) {
                row += value.size();
            }
        }
        return row;
    }

    /**
     * 获取总行数的一半行数
     * 如果总行数为偶数，返回数组为 [size / 2, size / 2]
     * 如果总行数为奇数，返回数组为 [size / 2, size / 2 + 1]
     * @return
     */
    public int[] getHalfRows() {
        int[] halfRows = new int[2];
        int allRows = getAllRows();
        halfRows[0] = allRows / 2;
        if (allRows % 2 == 0) {
            // 偶数
            halfRows[1] = allRows / 2;
        } else {
            halfRows[1] = allRows / 2 + 1;
        }
        return halfRows;
    }

    // 获取每一车的所有的行数
    public int getRowsByTrain(String train) {
        int row = 0;
        List<String> list = mWorkNameByTrainMap.get(train);
        if (list != null) {
            row = list.size();
        }
        return row;
    }

    /**
     * 获取每一车总行数的一半行数
     * 如果总行数为偶数，返回数组为 [size / 2, size / 2]
     * 如果总行数为奇数，返回数组为 [size / 2, size / 2 + 1]
     * @return
     */
    public int[] getHalfRowsByTrain(String train) {
        int[] halfRows = new int[2];
        int allRows = getRowsByTrain(train);
        halfRows[0] = allRows / 2;
        if (allRows % 2 == 0) {
            // 偶数
            halfRows[1] = allRows / 2;
        } else {
            halfRows[1] = allRows / 2 + 1;
        }
        return halfRows;
    }

    public Map<String, Map<String, Map<String, BigDecimal>>> getEachTrainWorkRecordMap() {
        return mEachTrainWorkRecordMap;
    }

    public void setEachTrainWorkRecordMap(Map<String, Map<String, Map<String, BigDecimal>>> eachTrainWorkRecordMap) {
        mEachTrainWorkRecordMap.clear();
        mEachTrainWorkRecordMap.putAll(eachTrainWorkRecordMap);
    }

    /**
     * 获取每车的总重量、白绒总重量、灰绒总重量
     * @param train
     * @return
     */
    public BigDecimal[] getTrainAllAndWhiteAndGrayWeight(String train) {
        BigDecimal[] bigDecimals = new BigDecimal[3];
        BigDecimal all = new BigDecimal(0).setScale(1, BigDecimal.ROUND_HALF_UP);
        BigDecimal white = new BigDecimal(0).setScale(1, BigDecimal.ROUND_HALF_UP);
        BigDecimal gray = new BigDecimal(0).setScale(1, BigDecimal.ROUND_HALF_UP);
        Map<String, Map<String, BigDecimal>> workRecordMap = mEachTrainWorkRecordMap.get(train);
        if (workRecordMap != null) {
            for (Map.Entry<String, Map<String, BigDecimal>> workRecord : workRecordMap.entrySet()) {
                if (workRecord != null) {
                    Map<String, BigDecimal> workRecordValue = workRecord.getValue();
                    if (workRecordValue != null) {
                        for (Map.Entry<String, BigDecimal> entry : workRecordValue.entrySet()) {
                            String key = entry.getKey();
                            BigDecimal value = entry.getValue();
                            if (value != null) {
                                all = all.add(value);
                                if (Kind.WHITE.getKind().equals(key)) {
                                    white = white.add(value);
                                } else if (Kind.GRAY.getKind().equals(key)) {
                                    gray = gray.add(value);
                                }
                            }
                        }
                    }
                }
            }
        }
        bigDecimals[0] = all;
        bigDecimals[1] = white;
        bigDecimals[2] = gray;
        return bigDecimals;
    }

    /**
     * 获取总重量、白绒总重量、灰绒总重量
     * @return
     */
    public BigDecimal[] getAllAndWhiteAndGrayWeight() {
        BigDecimal[] bigDecimals = new BigDecimal[3];
        BigDecimal all = new BigDecimal(0).setScale(1, BigDecimal.ROUND_HALF_UP);
        BigDecimal white = new BigDecimal(0).setScale(1, BigDecimal.ROUND_HALF_UP);
        BigDecimal gray = new BigDecimal(0).setScale(1, BigDecimal.ROUND_HALF_UP);
        for (Map.Entry<String, Map<String, Map<String, BigDecimal>>> eachTrainWorkRecordMap : mEachTrainWorkRecordMap.entrySet()) {
            if (eachTrainWorkRecordMap != null) {
                Map<String, Map<String, BigDecimal>> workRecordMap = eachTrainWorkRecordMap.getValue();
                if (workRecordMap != null) {
                    for (Map.Entry<String, Map<String, BigDecimal>> workRecord : workRecordMap.entrySet()) {
                        if (workRecord != null) {
                            Map<String, BigDecimal> workRecordValue = workRecord.getValue();
                            if (workRecordValue != null) {
                                for (Map.Entry<String, BigDecimal> entry : workRecordValue.entrySet()) {
                                    String key = entry.getKey();
                                    BigDecimal value = entry.getValue();
                                    if (value != null) {
                                        all = all.add(value);
                                        if (Kind.WHITE.getKind().equals(key)) {
                                            white = white.add(value);
                                        } else if (Kind.GRAY.getKind().equals(key)) {
                                            gray = gray.add(value);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        bigDecimals[0] = all;
        bigDecimals[1] = white;
        bigDecimals[2] = gray;
        return bigDecimals;
    }

}
