package com.terwergreen.jvueserver.util;

import com.terwergreen.jvueserver.constant.JVueConstants;

/**
 * 属性枚举
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/24 21:00
 **/
public enum MetaEnum {
    /**
     * 属性枚举
     */
    META_ENUM_CATEGORY(JVueConstants.META_TYPE_CATEGORY, 1),
    META_ENUM_TAG(JVueConstants.META_TYPE_TAG, 1),
    META_ENUM_COUNTER(JVueConstants.META_TYPE_COUNTER, 1);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    MetaEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (MetaEnum m : MetaEnum.values()) {
            if (m.getIndex() == index) {
                return m.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
