package com.terwergreen.jvueserver.core;

import java.util.List;
import java.util.Map;

/**
 * 提供公共数据库增删改查
 *
 * @author Terwer
 * @version 1.0
 * 2018/3/31 15:07
 **/
public interface CommonDAO {
    /**
     * 查询列表
     *
     * @param sql sql
     * @return 集合
     */
    List queryList(String sql);

    /**
     * 查询列表
     *
     * @param sql sql
     * @param str sql名称
     * @return List 集合
     */
    List queryListByString(String sql, String str);

    /**
     * 查询列表
     *
     * @param sql     sql
     * @param paraMap 参数
     * @return List 集合
     */
    List queryListByMap(String sql, Map<String, Object> paraMap);

    /**
     * 查询列表
     *
     * @param sql    sql
     * @param object 参数
     * @return List 集合
     */
    List queryListByObject(String sql, Object object);

    /**
     * 分页查询
     *
     * @param sql      sql
     * @param paraMap  查询条件
     * @param start    起始条目
     * @param pageSize 每页显示条目
     * @return 集合
     */
    List queryPageList(String sql, Map<String, Object> paraMap, int start, int pageSize);

    /**
     * 分页查询
     *
     * @param sql      sql
     * @param str      查询条件
     * @param start    起始条目
     * @param pageSize 每页显示条目
     * @return 集合
     */
    List queryPageListByString(String sql, String str, int start, int pageSize);

    /**
     * 分页查询
     *
     * @param sql      查询条件
     * @param paraMap  参数
     * @param start    起始条目
     * @param pageSize 每页显示条目
     * @return 集合
     */
    List queryPageListByMap(String sql, Map<String, Object> paraMap, int start, int pageSize);

    /**
     * 分页查询
     *
     * @param sql      sql
     * @param object   查询条件
     * @param start    起始条目
     * @param pageSize 每页显示条目
     * @return 集合
     */
    List queryPageListByObject(String sql, Object object, int start, int pageSize);

    /**
     * 查询单个信息
     *
     * @param sql sql
     * @return 结果
     */
    Object querySingleByString(String sql);


    /**
     * 查询单个信息
     *
     * @param sql sql
     * @param str sql名称
     * @return Object 结果
     */
    Object querySingleByString(String sql, String str);

    /**
     * 查询单个信息
     *
     * @param sql     sql
     * @param paraMap 参数
     * @return Object 结果
     */
    Object querySingleByMap(String sql, Map<String, Object> paraMap);

    /**
     * 查询单个信息
     *
     * @param sql    sql
     * @param object 参数
     * @return Object 结果
     */
    Object querySingleByObject(String sql, Object object);

    /**
     * 新增
     *
     * @param sql     sql
     * @param paraMap 参数
     * @return Object 结果
     */
    Object insert(String sql, Map<String, Object> paraMap);

    /**
     * 新增
     *
     * @param sql    sql
     * @param object 参数
     * @return Object 结果
     */
    Object insertByObject(String sql, Object object);

    /**
     * 删除
     *
     * @param sql     sql
     * @param paraMap 参数
     * @return int 结果
     */
    int delete(String sql, Map<String, Object> paraMap);

    /**
     * 删除
     *
     * @param sql    sql
     * @param object 参数
     * @return int 结果
     */
    int deleteByObject(String sql, Object object);

    /**
     * 检核删除是否成功
     *
     * @param sql     sql
     * @param paraMap 参数
     * @return true:成功  false:失败
     */
    boolean checkDelete(String sql, Map<String, Object> paraMap);

    /**
     * 检核删除是否成功
     *
     * @param sql    sql
     * @param object 参数
     * @return true:成功  false:失败
     */
    boolean checkDeleteByObject(String sql, Object object);

    /**
     * 更新
     *
     * @param sql     sql
     * @param paraMap 参数
     * @return int
     */
    int update(String sql, Map<String, Object> paraMap);

    /**
     * 更新
     *
     * @param sql    sql
     * @param object 参数
     * @return int
     */
    int updateByObject(String sql, Object object);

    /**
     * 检核更新是否成功
     *
     * @param sql     sql
     * @param paraMap 参数
     * @return true:成功  false:失败
     */
    boolean checkUpdate(String sql, Map<String, Object> paraMap);

    /**
     * 检核更新是否成功
     *
     * @param sql    sql
     * @param object 参数
     * @return true:成功  false:失败
     */
    boolean checkUpdateByObject(String sql, Object object);

    /**
     * 批量新增
     *
     * @param sql        sql
     * @param insertList 参数
     */
    void insertBatch(String sql, List insertList);

    /**
     * 批量更新
     *
     * @param sql        sql
     * @param updateList 参数
     */
    void updateBatch(String sql, List updateList);
}