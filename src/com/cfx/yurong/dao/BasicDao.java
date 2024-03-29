package com.cfx.yurong.dao;


import com.cfx.yurong.utils.JdbcUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Project: Ajax
 * Create By: Chen.F.X
 * DateTime: 2024/1/7 15:30
 * <p>
 * 开发 BasicDAO, 是其他 DAO 的父类, 使用到 apache-dbutils
 **/
public class BasicDao<T> {
    private QueryRunner mQueryRunner = new QueryRunner();

    // 开发通用的 dml 方法, 针对任意的表
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JdbcUtilsByDruid.getConnection();
            int update = mQueryRunner.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常 -> 运行异常, 抛出
        } finally {
            JdbcUtilsByDruid.close(null, null, connection);
        }
    }

    /**
     * 返回多个对象(即查询的结果是多行), 针对任意表
     *
     * @param sql        sql 语句，可以有 ?
     * @param clazz      传入一个类的 Class 对象 比如 Actor.class
     * @param parameters 传入 ? 的具体的值， 可以是多个
     * @return 根据 Actor.class 返回对应的 ArrayList 集合
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JdbcUtilsByDruid.getConnection();
            return mQueryRunner.query(connection, sql, new BeanListHandler<>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常 -> 运行异常, 抛出
        } finally {
            JdbcUtilsByDruid.close(null, null, connection);
        }
    }

    // 查询单行结果的通用方法
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JdbcUtilsByDruid.getConnection();
            return mQueryRunner.query(connection, sql, new BeanHandler<>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常 -> 运行异常, 抛出
        } finally {
            JdbcUtilsByDruid.close(null, null, connection);
        }
    }

    // 查询单行单列的方法, 即返回单值的方法
    public Object queryScalar(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JdbcUtilsByDruid.getConnection();
            return mQueryRunner.query(connection, sql, new ScalarHandler(), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常 -> 运行异常, 抛出
        } finally {
            JdbcUtilsByDruid.close(null, null, connection);
        }
    }

    // 查询单列的多行个数据
    public List<String> queryArrayList(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JdbcUtilsByDruid.getConnection();
            return mQueryRunner.query(connection, sql, new ColumnListHandler<>(), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常 -> 运行异常, 抛出
        } finally {
            JdbcUtilsByDruid.close(null, null, connection);
        }
    }
}
