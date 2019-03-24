package com.terwergreen.jvueserver.core;

import com.terwergreen.jvueserver.model.SiteConfig;

/**
 * 公共服务
 *
 * @author Terwer
 * @version 1.0
 * 2018/11/26 15:12
 **/
public interface CommonService {
    /**
     * 获取站点配置
     *
     * @return 站点配置
     */
    SiteConfig getSiteConfig();

    /**
     * 获取站点配置项
     *
     * @param optionName 配置名
     * @return 站点配置项
     */
    Object getSiteConfigItem(String optionName);

    /**
     * 获取属性
     *
     * @param optionGroup 属性组
     * @return 属性
     */
    Object getOption(String optionGroup);

    /**
     * 更新单个站点配置项
     *
     * @param optionName     配置名
     * @param newOptionValue 配置值
     * @return 结果
     */
    boolean updateSiteConfig(String optionName, String newOptionValue);

    /**
     * 更新属性
     *
     * @param optionName  配置名
     * @param optionGroup 配置值
     * @return 结果
     */
    boolean updateOption(String optionName, String optionValue, String optionGroup);
}