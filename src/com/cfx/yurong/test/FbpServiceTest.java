package com.cfx.yurong.test;

import com.cfx.yurong.entity.DateKind;
import com.cfx.yurong.entity.FbpItem;
import com.cfx.yurong.service.IFbpService;
import com.cfx.yurong.service.serviceimpl.FbpServiceImpl;
import com.cfx.yurong.utils.Logit;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/23 11:31
 **/
public class FbpServiceTest {
    private static final String TAG = "FbpServiceTest";
    private IFbpService mFbpService = new FbpServiceImpl();

    // 根据员工名返回对应的 FbpItem 集合
    @Test
    public void queryFbpItemsByName() {
        List<FbpItem> fbpItems = mFbpService.queryFbpItemsByName("张三");
        Logit.d(TAG, "fbpItems: " + fbpItems);
        Logit.d(TAG, "size: " + fbpItems.size());
    }

    // 保存 FbpItem 对象到数据库中
    @Test
    public void saveFbpItemItem() {
        FbpItem fbpItem = new FbpItem();
        fbpItem.setWorkName("徐鑫");
        fbpItem.setWorkTime(new Date());
        fbpItem.setDateKind(DateKind.AM.getKind());
        fbpItem.setWorkHour(new BigDecimal(0.5));
        boolean b = mFbpService.saveFbpItemItem(fbpItem);
        Logit.d(TAG, "b: " + b);
    }

    // 返回所有的风包皮员工姓名
    @Test
    public void getAllWorkName() {
        List<String> allWorkName = mFbpService.getAllWorkName();
        Logit.d(TAG, "allWorkName: " + allWorkName);
        Logit.d(TAG, "size: " + allWorkName.size());
    }

    // 根据 id，查询 FbpItem 信息
    @Test
    public void getFbpItemById() {
        FbpItem item = mFbpService.getFbpItemById(3);
        Logit.d(TAG, "item: " + item);
    }

    // 更新某条 id 的风包皮记录信息
    @Test
    public void updateFbpItem() {
        FbpItem fbpItem = new FbpItem();
        fbpItem.setId(6);
        fbpItem.setWorkName("徐鑫");
        fbpItem.setWorkTime(new Date());
        fbpItem.setDateKind(DateKind.PM.getKind());
        fbpItem.setWorkHour(new BigDecimal(0.5));
        boolean b = mFbpService.updateFbpItem(fbpItem);
        Logit.d(TAG, "b: " + b);
    }

    // 根据 id 删除记录信息
    @Test
    public void deleteFbpItem() {
        boolean b = mFbpService.deleteFbpItem(4);
        Logit.d(TAG, "b: " + b);
    }
}
