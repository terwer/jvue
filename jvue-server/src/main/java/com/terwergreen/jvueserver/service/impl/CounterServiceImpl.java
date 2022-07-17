package com.terwergreen.jvueserver.service.impl;

import com.terwergreen.jvueserver.constant.JVueConstants;
import com.terwergreen.jvueserver.model.Meta;
import com.terwergreen.jvueserver.service.ICounterService;
import com.terwergreen.jvueserver.service.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 计数实现
 *
 * @name: CounterServiceImpl
 * @author: terwer
 * @date: 2022-07-17 23:37
 **/
@Service
public class CounterServiceImpl implements ICounterService {
    @Autowired
    private MetaService metaService;

    @Transactional
    @Override
    public int getCounter() {
        Meta meta = metaService.getMetaSingle(JVueConstants.META_TYPE_COUNTER);
        Integer counter = 0;

        if (null != meta && StringUtils.isNotEmpty(meta.getName())) {
            counter = Integer.valueOf(meta.getName());
        }
        ++counter;

        if(null == meta){
            metaService.saveMeta(String.valueOf(counter),JVueConstants.META_TYPE_COUNTER);
        }else{
            metaService.updateMetaSingle(String.valueOf(counter), JVueConstants.META_TYPE_COUNTER);
        }
        return counter;
    }
}
