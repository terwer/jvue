package com.terwergreen.jvueserver.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteConfig {
    private String domain;
    private String weburl;
    private String webtheme;
    private String webname;
    private String webslogen;
    private String keywords;
    private String description;
    private String beianinfo;
    private String debug;
    private String adminpath;
}