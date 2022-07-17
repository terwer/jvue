package com.terwergreen.jvueserver.service;

/**
 * 统计计数
 *
 * @name: ICounterService
 * @author: terwer
 * @date: 2022-07-17 23:36
 **/
public interface ICounterService {
    /**
     * 获取自动递增的计数
     * @return
     */
    int getCounter();
}
