package com.cfx.yurong.dao.daoimpl;

import com.cfx.yurong.dao.BasicDao;
import com.cfx.yurong.dao.IFbpDao;
import com.cfx.yurong.entity.FbpItem;
import com.cfx.yurong.utils.Logit;

import java.util.List;

import static com.cfx.yurong.utils.Constants.TABLE_FBP_2024;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/23 11:15
 **/
public class FbpDaoImpl extends BasicDao<FbpItem> implements IFbpDao {
    private static final String TAG = "FbpDaoImpl";

    @Override
    public List<FbpItem> queryFbpItemsByName(String workerName) {
        if (workerName == null || "".equals(workerName)) {
            Logit.d(TAG, "workerName is null");
            return null;
        }
        String sql = "select id, work_name workName, work_time workTime, date_kind dateKind, work_hour workHour from " + TABLE_FBP_2024 + " where work_name = ?";
        return queryMulti(sql, FbpItem.class, workerName);
    }

    @Override
    public boolean saveFbpItemItem(FbpItem fbpItem) {
        if (fbpItem == null || fbpItem.getWorkName() == null || "".equals(fbpItem.getWorkName())) {
            Logit.d(TAG, "fbpItem is null");
            return false;
        }
        String sql = "insert into " + TABLE_FBP_2024 + " values(?, ?, ?, ?, ?);";
        int update =  update(sql, fbpItem.getId(), fbpItem.getWorkName(), fbpItem.getWorkTime(), fbpItem.getDateKind(), fbpItem.getWorkHour());
        return update > 0;
    }

    @Override
    public List<String> getAllWorkName() {
        String sql = "select distinct work_name from " + TABLE_FBP_2024;
        return queryArrayList(sql);
    }

    @Override
    public FbpItem getFbpItemById(Integer id) {
        if (id == null || id <= 0) {
            Logit.d(TAG, "input id is error");
            return null;
        }
        String sql = "select id, work_name workName, work_time workTime, date_kind dateKind, work_hour workHour from " + TABLE_FBP_2024 + " where id = ?";
        return querySingle(sql, FbpItem.class, id);
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
        String sql = "update " + TABLE_FBP_2024 + " set work_name = ?, work_time = ?, date_kind = ?, work_hour = ? where id = ?";
        int update =  update(sql, fbpItem.getWorkName(), fbpItem.getWorkTime(), fbpItem.getDateKind(), fbpItem.getWorkHour(),
                fbpItem.getId());
        return update > 0;
    }

    @Override
    public boolean deleteFbpItem(Integer id) {
        if (id == null || id <= 0) {
            Logit.d(TAG, "input id is null");
            return false;
        }
        String sql = "delete from " + TABLE_FBP_2024 + " where id = ?";
        int update = update(sql, id);
        return update > 0;
    }
}
