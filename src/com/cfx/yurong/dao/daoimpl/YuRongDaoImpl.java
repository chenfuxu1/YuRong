package com.cfx.yurong.dao.daoimpl;

import com.cfx.yurong.dao.BasicDao;
import com.cfx.yurong.dao.IYuRongDao;
import com.cfx.yurong.entity.Kind;
import com.cfx.yurong.entity.YuRongItem;
import com.cfx.yurong.utils.Logit;

import java.util.List;

import static com.cfx.yurong.utils.Constants.TABLE_YU_RONG_2024;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/17 14:38
 *
 * 完成对羽绒表的增删查改操作
 **/
public class YuRongDaoImpl extends BasicDao<YuRongItem> implements IYuRongDao {
    private static final String TAG = "YuRongDaoImpl";

    @Override
    public List<YuRongItem> queryYuRongByName(String workerName) {
        if (workerName == null || "".equals(workerName)) {
            Logit.d(TAG, "workerName is null");
            return null;
        }
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024 + " where work_name = ?";
        return queryMulti(sql, YuRongItem.class, workerName);
    }

    @Override
    public boolean saveYuRongItem(YuRongItem yuRongItem) {
        if (yuRongItem == null || yuRongItem.getWorkName() == null || "".equals(yuRongItem.getWorkName())) {
            Logit.d(TAG, "yuRongItem is null");
            return false;
        }
        String sql = "insert into " + TABLE_YU_RONG_2024 + " values(?, ?, ?, ?, ?, ?);";
        int update =  update(sql, yuRongItem.getId(), yuRongItem.getWorkName(), yuRongItem.getWorkTime(), yuRongItem.getHeight(), yuRongItem.getKind(), yuRongItem.getTrainNumber());
        return update > 0;
    }

    @Override
    public List<YuRongItem> getAllYuRongItems() {
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024;
        return queryMulti(sql, YuRongItem.class);
    }

    @Override
    public List<YuRongItem> getAllWhiteYuRongItems() {
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024 + " where kind = ?";
        return queryMulti(sql, YuRongItem.class, Kind.WHITE.getKind());
    }

    @Override
    public List<YuRongItem> getAllGrayYuRongItems() {
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024 + " where kind = ?";
        return queryMulti(sql, YuRongItem.class, Kind.GRAY.getKind());
    }

    @Override
    public List<YuRongItem> getAllYuRongItemsByTrainNum(String trainNumber) {
        if (trainNumber == null || "".equals(trainNumber)) {
            Logit.d(TAG, "trainNumber is null");
            return null;
        }
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024 + " where train_number = ?";
        return queryMulti(sql, YuRongItem.class, trainNumber);
    }

    @Override
    public List<YuRongItem> getAllWhiteYuRongItemsByTrainNum(String trainNumber) {
        if (trainNumber == null || "".equals(trainNumber)) {
            Logit.d(TAG, "trainNumber is null");
            return null;
        }
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024 + " where train_number = ? and kind = ?";
        return queryMulti(sql, YuRongItem.class, trainNumber, Kind.WHITE.getKind());
    }

    @Override
    public List<YuRongItem> getAllGrayYuRongItemsByTrainNum(String trainNumber) {
        if (trainNumber == null || "".equals(trainNumber)) {
            Logit.d(TAG, "trainNumber is null");
            return null;
        }
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024 + " where train_number = ? and kind = ?";
        return queryMulti(sql, YuRongItem.class, trainNumber, Kind.GRAY.getKind());
    }

    @Override
    public List<YuRongItem> getYuRongByNameAndNum(String workerName, String trainNum) {
        if (workerName == null || "".equals(workerName) || trainNum == null || "".equals(trainNum)) {
            Logit.d(TAG, "workerName or trainNumber is null");
            return null;
        }
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024 + " where work_name = ? and train_number = ?";
        return queryMulti(sql, YuRongItem.class, workerName, trainNum);
    }

    @Override
    public List<YuRongItem> getWhiteYuRongByNameAndNum(String workerName, String trainNum) {
        if (workerName == null || "".equals(workerName) || trainNum == null || "".equals(trainNum)) {
            Logit.d(TAG, "workerName or trainNumber is null");
            return null;
        }
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024 + " where work_name = ? and train_number = ? and kind = ?";
        return queryMulti(sql, YuRongItem.class, workerName, trainNum, Kind.WHITE.getKind());
    }

    @Override
    public List<YuRongItem> getGrayYuRongByNameAndNum(String workerName, String trainNum) {
        if (workerName == null || "".equals(workerName) || trainNum == null || "".equals(trainNum)) {
            Logit.d(TAG, "workerName or trainNumber is null");
            return null;
        }
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024 + " where work_name = ? and train_number = ? and kind = ?";
        return queryMulti(sql, YuRongItem.class, workerName, trainNum, Kind.GRAY.getKind());
    }

    @Override
    public List<String> getAllWorkName() {
        String sql = "select distinct work_name from " + TABLE_YU_RONG_2024;
        return queryArrayList(sql);
    }

    @Override
    public YuRongItem getYuRongItemById(Integer id) {
        if (id == null || id <= 0) {
            Logit.d(TAG, "input id is error");
            return null;
        }
        String sql = "select id, work_name workName, work_time workTime, height, kind, train_number trainNumber from " + TABLE_YU_RONG_2024 + " where id = ?";
        return querySingle(sql, YuRongItem.class, id);
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
        String sql = "update " + TABLE_YU_RONG_2024 + " set work_name = ?, work_time = ?, height = ?, kind = ?, train_number = ? where id = ?";
        int update =  update(sql, yuRongItem.getWorkName(), yuRongItem.getWorkTime(), yuRongItem.getHeight(), yuRongItem.getKind(),
                yuRongItem.getTrainNumber(), yuRongItem.getId());
        return update > 0;
    }

    @Override
    public boolean deleteYuRongItem(Integer id) {
        if (id == null || id <= 0) {
            Logit.d(TAG, "input id is null");
            return false;
        }
        String sql = "delete from " + TABLE_YU_RONG_2024 + " where id = ?";
        int update = update(sql, id);
        return update > 0;
    }

    @Override
    public List<String> getAllWorkNamesByTrainNum(String trainNum) {
        String sql = "select distinct work_name from " + TABLE_YU_RONG_2024 + " where train_number = ?";
        return queryArrayList(sql, trainNum);
    }

    @Override
    public List<String> getAllTrains() {
        String sql = "select distinct train_number from " + TABLE_YU_RONG_2024;
        return queryArrayList(sql);
    }
}
