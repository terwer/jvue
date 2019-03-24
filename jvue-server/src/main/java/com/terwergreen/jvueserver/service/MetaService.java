package com.terwergreen.jvueserver.service;

import com.terwergreen.jvueserver.model.Meta;

import java.util.List;

/**
 *  属性 Service 接口
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/24 20:31
 **/
public interface MetaService {
    /**
     * 根据属性以及属性下的已发布文章
     *
     * @param type 属性类型
     * @return List<MetaDto>
     */
//    List<MetaDto> getMetaDtos(String type);

    /**
     * 根据属性
     *
     * @param type 属性类型
     * @return List<MetaDto>
     */
    List<Meta> getMetaList(String type);

    /**
     * 删除属性(同时删除关联文章的属性)
     *
     * @param name 属性名
     * @param type 属性类型
     * @return boolean
     */
    boolean deleteMeta(String name, String type);

    /**
     * 保存属性
     *
     * @param name 属性名
     * @param type 属性类型
     * @return boolean
     */
    boolean saveMeta(String name, String type);

    /**
     * 更新属性(同时更新关联文章的属性)
     *
     * @param id   属性id
     * @param name 属性名
     * @param type 属性类型
     * @return boolean
     */
    boolean updateMeta(Integer id, String name, String type);

    /**
     * 添加或者删除属性(同时添加或者删除关联文章的属性)
     *
     * @param names     属性名
     * @param type      属性类型
     * @param postId 关联文章id
     * @return boolean
     */
    boolean saveOrRemoveMetas(String names, String type, Integer postId);
}
