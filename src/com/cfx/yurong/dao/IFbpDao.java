package com.cfx.yurong.dao;

import com.cfx.yurong.entity.FbpItem;

import java.util.List;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/23 11:10
 *
 * 完成对风包皮表的增删查改操作
 **/
public interface IFbpDao {
    // 根据员工名返回对应的 FbpItem 集合
    public List<FbpItem> queryFbpItemsByName(String workerName);

    // 保存 FbpItem 对象到数据库中
    public boolean saveFbpItemItem(FbpItem fbpItem);

    // 返回所有的风包皮员工姓名
    public List<String> getAllWorkName();

    // 根据 id，查询 FbpItem 信息
    public FbpItem getFbpItemById(Integer id);

    // 更新某条 id 的风包皮记录信息
    public boolean updateFbpItem(FbpItem fbpItem);

    // 根据 id 删除记录信息
    public boolean deleteFbpItem(Integer id);
}
