package com.cfx.yurong.dao;

import com.cfx.yurong.entity.YuRongItem;

import java.util.List;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/17 14:34
 *
 * 完成对羽绒表的增删查改操作
 **/
public interface IYuRongDao {
    // 根据员工名返回对应的 YuRongItem 集合
    public List<YuRongItem> queryYuRongByName(String workerName);

    // 保存 YuRongItem 对象到数据库中
    public boolean saveYuRongItem(YuRongItem yuRongItem);

    // 查询数据表中所有的信息
    public List<YuRongItem> getAllYuRongItems();

    // 查询所有的白羽绒信息
    public List<YuRongItem> getAllWhiteYuRongItems();

    // 查询所有的灰羽绒信息
    public List<YuRongItem> getAllGrayYuRongItems();

    // 查询数据表中某一车次的所有的信息
    public List<YuRongItem> getAllYuRongItemsByTrainNum(String trainNumber);

    // 查询数据表中某一车次的所有白绒的信息
    public List<YuRongItem> getAllWhiteYuRongItemsByTrainNum(String trainNumber);

    // 查询数据表中某一车次的所有灰绒的信息
    public List<YuRongItem> getAllGrayYuRongItemsByTrainNum(String trainNumber);

    // 根据员工名，已及车次返回某个员工某一车次的所有 YuRongItem 集合
    public List<YuRongItem> getYuRongByNameAndNum(String workerName, String trainNum);

    // 根据员工名，已及车次返回某个员工某一车次的白绒 YuRongItem 集合
    public List<YuRongItem> getWhiteYuRongByNameAndNum(String workerName, String trainNum);

    // 根据员工名，已及车次返回某个员工某一车次的灰绒 YuRongItem 集合
    public List<YuRongItem> getGrayYuRongByNameAndNum(String workerName, String trainNum);

    // 返回所有的员工姓名
    public List<String> getAllWorkName();

    // 根据 id，查询 YuRongItem 信息
    public YuRongItem getYuRongItemById(Integer id);

    // 更新某条 id 的账单记录信息
    public boolean updateYuRongItem(YuRongItem yuRongItem);

    // 根据 id 删除记录信息
    public boolean deleteYuRongItem(Integer id);

    // 根据车次，查询有哪些员工参与工作了
    public List<String> getAllWorkNamesByTrainNum(String trainNum);

    // 查询有哪些车次
    public List<String> getAllTrains();

}
