package com.terwergreen.jvue.vue;

import java.util.Map;

/**
 * 服务端渲染Vue
 */
public interface VueRenderer {
    /**
     * 根据上下文渲染实例
     * @param context 上下文
     * @return 服务端html及对应状态
     */
    Map<String,Object> renderContent(Map<String, Object> context);
}