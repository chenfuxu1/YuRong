package com.cfx.yurong.service.serviceimpl;

import com.cfx.yurong.dao.IYuRongDao;
import com.cfx.yurong.dao.daoimpl.YuRongDaoImpl;
import com.cfx.yurong.entity.AllYuRongRecordItem;
import com.cfx.yurong.entity.Kind;
import com.cfx.yurong.entity.YuRongItem;
import com.cfx.yurong.service.IYuRongService;
import com.cfx.yurong.utils.Logit;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/17 15:14
 **/
public class YuRongServiceImpl implements IYuRongService {
    private static final String TAG = "YuRongServiceImpl";
    private IYuRongDao mYuRongDao = new YuRongDaoImpl();

    @Override
    public List<YuRongItem> queryYuRongByName(String workerName) {
        if (workerName == null || "".equals(workerName)) {
            Logit.d(TAG, "worker name is null");
            return null;
        }
        return mYuRongDao.queryYuRongByName(workerName);
    }

    @Override
    public boolean saveYuRongItem(YuRongItem yuRongItem) {
        if (yuRongItem == null || yuRongItem.getWorkName() == null || "".equals(yuRongItem.getWorkName())) {
            Logit.d(TAG, "yuRongItem is null");
            return false;
        }
        return mYuRongDao.saveYuRongItem(yuRongItem);
    }

    @Override
    public List<YuRongItem> getAllYuRongItems() {
        return mYuRongDao.getAllYuRongItems();
    }

    @Override
    public List<YuRongItem> getAllWhiteYuRongItems() {
        return mYuRongDao.getAllWhiteYuRongItems();
    }

    @Override
    public List<YuRongItem> getAllGrayYuRongItems() {
        return mYuRongDao.getAllGrayYuRongItems();
    }

    @Override
    public List<YuRongItem> getAllYuRongItemsByTrainNum(String trainNumber) {
        if (trainNumber == null || "".equals(trainNumber)) {
            Logit.d(TAG, "trainNumber is null");
            return null;
        }
        return mYuRongDao.getAllYuRongItemsByTrainNum(trainNumber);
    }

    @Override
    public List<YuRongItem> getAllWhiteYuRongItemsByTrainNum(String trainNumber) {
        if (trainNumber == null || "".equals(trainNumber)) {
            Logit.d(TAG, "trainNumber is null");
            return null;
        }
        return mYuRongDao.getAllWhiteYuRongItemsByTrainNum(trainNumber);
    }

    @Override
    public List<YuRongItem> getAllGrayYuRongItemsByTrainNum(String trainNumber) {
        if (trainNumber == null || "".equals(trainNumber)) {
            Logit.d(TAG, "trainNumber is null");
            return null;
        }
        return mYuRongDao.getAllGrayYuRongItemsByTrainNum(trainNumber);
    }

    @Override
    public List<YuRongItem> getYuRongByNameAndNum(String workerName, String trainNum) {
        if (workerName == null || "".equals(workerName) || trainNum == null || "".equals(trainNum)) {
            Logit.d(TAG, "workerName or trainNumber is null");
            return null;
        }
        return mYuRongDao.getYuRongByNameAndNum(workerName, trainNum);
    }

    @Override
    public List<YuRongItem> getWhiteYuRongByNameAndNum(String workerName, String trainNum) {
        if (workerName == null || "".equals(workerName) || trainNum == null || "".equals(trainNum)) {
            Logit.d(TAG, "workerName or trainNumber is null");
            return null;
        }
        return mYuRongDao.getWhiteYuRongByNameAndNum(workerName, trainNum);
    }

    @Override
    public List<YuRongItem> getGrayYuRongByNameAndNum(String workerName, String trainNum) {
        if (workerName == null || "".equals(workerName) || trainNum == null || "".equals(trainNum)) {
            Logit.d(TAG, "workerName or trainNumber is null");
            return null;
        }
        return mYuRongDao.getGrayYuRongByNameAndNum(workerName, trainNum);
    }

    @Override
    public List<String> getAllWorkName() {
        return mYuRongDao.getAllWorkName();
    }

    @Override
    public YuRongItem getYuRongItemById(Integer id) {
        if (id == null || id <= 0) {
            Logit.d(TAG, "input id is error");
            return null;
        }
        return mYuRongDao.getYuRongItemById(id);
    }

    @Override
    public boolean updateYuRongItem(YuRongItem yuRongItem) {
        if (yuRongItem == null) {
            Logit.d(TAG, "input yuRongItem is null");
            return false;
        }
        if (yuRongItem.getId() == null || yuRongItem.getId() <= 0) {
            Logit.d(TAG, "input id is error");
            return false;
        }
        return mYuRongDao.updateYuRongItem(yuRongItem);
    }

    @Override
    public boolean deleteYuRongItem(Integer id) {
        if (id == null || id <= 0) {
            Logit.d(TAG, "input id is null");
            return false;
        }
        return mYuRongDao.deleteYuRongItem(id);
    }

    @Override
    public AllYuRongRecordItem getAllYRRecords() {
        AllYuRongRecordItem allYuRongRecordItem = new AllYuRongRecordItem();

        // 1.设置所有的车次
        List<String> allTrains = mYuRongDao.getAllTrains();
        allYuRongRecordItem.setAllTrains(allTrains);

        // 2.设置每个车次，共有哪些员工信息
        Map<String, List<String>> workNamesMap = new HashMap<>();
        // 3.
        Map<String, Map<String, Map<String, BigDecimal>>> allRecordsMap = new HashMap<>();
        for (String train : allTrains) {
            List<String> workName = mYuRongDao.getAllWorkNamesByTrainNum(train);
            workNamesMap.put(train, workName);

            Map<String, Map<String, BigDecimal>> workRecordsMap = new HashMap<>();
            for (String name : workName) {
                Map<String, BigDecimal> recordMap = new HashMap<>();
                List<YuRongItem> yuRongItemList = mYuRongDao.getYuRongByNameAndNum(name, train);
                BigDecimal white = new BigDecimal(0).setScale(1, BigDecimal.ROUND_HALF_UP);
                BigDecimal gray = new BigDecimal(0).setScale(1, BigDecimal.ROUND_HALF_UP);
                for (YuRongItem yuRongItem : yuRongItemList) {
                    if (yuRongItem == null) {
                        continue;
                    }
                    if (Kind.WHITE.getKind().equals(yuRongItem.getKind())) {
                        white = white.add(yuRongItem.getHeight());
                    } else if (Kind.GRAY.getKind().equals(yuRongItem.getKind())) {
                        gray = gray.add(yuRongItem.getHeight());
                    }
                }
                recordMap.put(Kind.WHITE.getKind(), white);
                recordMap.put(Kind.GRAY.getKind(), gray);
                workRecordsMap.put(name, recordMap);
            }
            allRecordsMap.put(train, workRecordsMap);
        }
        allYuRongRecordItem.setWorkNameByTrainMap(workNamesMap);

        allYuRongRecordItem.setEachTrainWorkRecordMap(allRecordsMap);
        return allYuRongRecordItem;
    }
}
