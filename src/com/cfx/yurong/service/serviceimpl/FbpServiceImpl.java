package com.cfx.yurong.service.serviceimpl;

import com.cfx.yurong.dao.IFbpDao;
import com.cfx.yurong.dao.daoimpl.FbpDaoImpl;
import com.cfx.yurong.entity.FbpItem;
import com.cfx.yurong.service.IFbpService;
import com.cfx.yurong.utils.Logit;

import java.util.List;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/23 11:27
 **/
public class FbpServiceImpl implements IFbpService {
    private static final String TAG = "FbpServiceImpl";
    private IFbpDao mFbpDao = new FbpDaoImpl();

    @Override
    public List<FbpItem> queryFbpItemsByName(String workerName) {
        if (workerName == null || "".equals(workerName)) {
            Logit.d(TAG, "worker name is null");
            return null;
        }
        return mFbpDao.queryFbpItemsByName(workerName);
    }

    @Override
    public boolean saveFbpItemItem(FbpItem fbpItem) {
        if (fbpItem == null || fbpItem.getWorkName() == null || "".equals(fbpItem.getWorkName())) {
            Logit.d(TAG, "fbpItem is null");
            return false;
        }
        return mFbpDao.saveFbpItemItem(fbpItem);
    }

    @Override
    public List<String> getAllWorkName() {
        return mFbpDao.getAllWorkName();
    }

    @Override
    public FbpItem getFbpItemById(Integer id) {
        if (id == null || id <= 0) {
            Logit.d(TAG, "input id is error");
            return null;
        }
        return mFbpDao.getFbpItemById(id);
    }

    @Override
    public boolean updateFbpItem(FbpItem fbpItem) {
        if (fbpItem == null) {
            Logit.d(TAG, "input fbpItem is null");
            return false;
        }
        if (fbpItem.getId() == null || fbpItem.getId() <= 0) {
            Logit.d(TAG, "input id is error");
            return false;
        }
        return mFbpDao.updateFbpItem(fbpItem);
    }

    @Override
    public boolean deleteFbpItem(Integer id) {
        if (id == null || id <= 0) {
            Logit.d(TAG, "input id is null");
            return false;
        }
        return mFbpDao.deleteFbpItem(id);
    }
}
