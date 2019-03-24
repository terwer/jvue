package com.terwergreen.jvueserver.service.impl;

import com.terwergreen.jvueserver.core.CommonDAO;
import com.terwergreen.jvueserver.exception.TipException;
import com.terwergreen.jvueserver.model.Meta;
import com.terwergreen.jvueserver.service.MetaService;
import com.terwergreen.jvueserver.util.MetaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性 Service 实现类
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/24 20:34
 **/
@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private CommonDAO commonDAO;

//    @Override
//    public List<MetaDto> getMetaDtos(String type) {
//        type = verifyType(type);
//        return metasMapper.selectMetasDtoPublish(type);
//    }

    @Override
    public List<Meta> getMetaList(String type) {
        type = verifyType(type);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("type",type);
        List<Meta> metaList = commonDAO.queryListByMap("selectMeta", paramMap);
        return metaList;
    }

    @Override
    public boolean deleteMeta(String name, String type) {
        return false;
    }

    @Override
    public boolean saveMeta(String name, String type) {
        return false;
    }

    @Override
    public boolean updateMeta(Integer id, String name, String type) {
        return false;
    }

    @Override
    public boolean saveOrRemoveMetas(String names, String type, Integer postId) {
        return false;
    }

    /**
     * 验证Type是否为定义的
     *
     * @return 结果
     */
    private String verifyType(String type) {
        if (MetaEnum.META_ENUM_CATEGORY.getName().equals(type) || MetaEnum.META_ENUM_TAG.getName().equals(type)) {
            return type;
        }
        throw new TipException("传输的属性类型不合法");
    }

    /**
     * 从属性字符串中去除一个属性
     *
     * @param name  名称
     * @param metas 属性
     * @return 结果
     */
    private String resetMeta(String name, String metas) {
        String[] metaArr = metas.split(",");
        StringBuffer sb = new StringBuffer();
        for (String m : metaArr) {
            if (!name.equals(m)) {
                sb.append(",").append(m);
            }
        }
        if (sb.length() > 0) {
            return sb.substring(1);
        }
        return "";
    }
}
