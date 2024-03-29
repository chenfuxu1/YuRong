package com.cfx.yurong.test;

import com.cfx.yurong.dao.IYuRongDao;
import com.cfx.yurong.dao.daoimpl.YuRongDaoImpl;
import com.cfx.yurong.entity.Kind;
import com.cfx.yurong.entity.Train;
import com.cfx.yurong.entity.YuRongItem;
import com.cfx.yurong.utils.Logit;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.cfx.yurong.utils.Constants.TABLE_YU_RONG_2024;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/17 14:48
 **/
public class YuRongDaoTest {
    private IYuRongDao mYuRongDao = new YuRongDaoImpl();

    @Test
    public void queryYuRongByName() {
        List<YuRongItem> items = mYuRongDao.queryYuRongByName("张三");
        Logit.d("TAG", "items: " + items);
    }

    @Test
    public void saveYuRongItem() {
        YuRongItem yuRongItem = new YuRongItem();
        yuRongItem.setWorkName("张三");
        Date currentDate = new Date();
        yuRongItem.setWorkTime(currentDate);
        yuRongItem.setHeight(new BigDecimal("543.8"));
        yuRongItem.setKind(Kind.GRAY.getKind());
        yuRongItem.setTrainNumber(Train.THREE.getNumber());
        boolean success = mYuRongDao.saveYuRongItem(yuRongItem);
        Logit.d("TAG", "success: " + success);
    }

    @Test
    public void queryAllItems() {
        List<YuRongItem> items = mYuRongDao.getAllYuRongItems();
        Logit.d("TAG", "items: " + items);
        Logit.d("TAG", "size: " + items.size());
    }

    @Test
    public void queryAllWhiteItems() {
        List<YuRongItem> items = mYuRongDao.getAllWhiteYuRongItems();
        Logit.d("TAG", "items: " + items);
        Logit.d("TAG", "size: " + items.size());
    }

    @Test
    public void queryAllGrayItems() {
        List<YuRongItem> items = mYuRongDao.getAllGrayYuRongItems();
        Logit.d("TAG", "items: " + items);
        Logit.d("TAG", "size: " + items.size());
    }

    @Test
    public void getAllYuRongItemsByTrainNum() {
        List<YuRongItem> items = mYuRongDao.getAllYuRongItemsByTrainNum(Train.TWO.getNumber());
        Logit.d("TAG", "items: " + items);
        Logit.d("TAG", "size: " + items.size());
    }

    @Test
    public void getAllWhiteYuRongItemsByTrainNum() {
        List<YuRongItem> items = mYuRongDao.getAllWhiteYuRongItemsByTrainNum(Train.TWO.getNumber());
        Logit.d("TAG", "items: " + items);
        Logit.d("TAG", "size: " + items.size());
    }

    @Test
    public void getAllGrayYuRongItemsByTrainNum() {
        List<YuRongItem> items = mYuRongDao.getAllGrayYuRongItemsByTrainNum(Train.TWO.getNumber());
        Logit.d("TAG", "items: " + items);
        Logit.d("TAG", "size: " + items.size());
    }

    @Test
    public void getYuRongByNameAndNum() {
        List<YuRongItem> items = mYuRongDao.getYuRongByNameAndNum("张继红", Train.TWO.getNumber());
        Logit.d("TAG", "items: " + items);
        Logit.d("TAG", "size: " + items.size());
    }

    @Test
    public void getWhiteYuRongByNameAndNum() {
        List<YuRongItem> items = mYuRongDao.getWhiteYuRongByNameAndNum("张继红", Train.TWO.getNumber());
        Logit.d("TAG", "items: " + items);
        Logit.d("TAG", "size: " + items.size());
    }

    @Test
    public void getGrayYuRongByNameAndNum() {
        List<YuRongItem> items = mYuRongDao.getGrayYuRongByNameAndNum("张继红", Train.ONE.getNumber());
        Logit.d("TAG", "items: " + items);
        Logit.d("TAG", "size: " + items.size());
    }

    @Test
    public void getAllWorkName() {
        List<String> allWorkName = mYuRongDao.getAllWorkName();
        Logit.d("TAG", "allWorkName: " + allWorkName);
        Logit.d("TAG", "size: " + allWorkName.size());
    }

    @Test
    public void getYuRongItemById() {
        YuRongItem yuRongItemById = mYuRongDao.getYuRongItemById(6);
        Logit.d("TAG", "yuRongItemById: " + yuRongItemById);
    }

    @Test
    public void updateYuRongItem() {
        YuRongItem yuRongItem = new YuRongItem();
        yuRongItem.setId(6);
        yuRongItem.setWorkName("荒天帝");
        Date currentDate = new Date();
        yuRongItem.setWorkTime(currentDate);
        yuRongItem.setHeight(new BigDecimal("534.6"));
        yuRongItem.setKind(Kind.WHITE.getKind());
        yuRongItem.setTrainNumber(Train.THREE.getNumber());
        boolean b = mYuRongDao.updateYuRongItem(yuRongItem);
        Logit.d("TAG", "b: " + b);
    }

    @Test
    public void deleteYuRongItem() {
        boolean b = mYuRongDao.deleteYuRongItem(6);
        Logit.d("TAG", "b: " + b);
    }

    @Test
    public void getAllWorkNamesByTrainNum() {
        List<String> aa = mYuRongDao.getAllWorkNamesByTrainNum("第十车");
        Logit.d("TAG", "aa: " + aa);
    }

    @Test
    public void getAllTrains() {
        List<String> allTrains = mYuRongDao.getAllTrains();
        Logit.d("TAG", "allTrains: " + allTrains);
    }
}
