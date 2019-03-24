package com.terwergreen.jvueserver.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 属性(分类和标签) Model
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/24 20:24
 **/
@Getter
@Setter
public class Meta {
    /**
     * 属性名
     */
    private String name;

    /**
     * 属性类型
     */
    private String type;
}
