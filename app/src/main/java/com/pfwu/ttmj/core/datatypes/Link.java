package com.pfwu.ttmj.core.datatypes;

import java.io.Serializable;

public class Link implements Serializable {
    public enum LinkType {
        BaiduYun, BT, Magnet, Xuanfeng, Thunder, Other
    }

    private String link;
    private LinkType type;

    public LinkType getType() {
        return type;
    }

    public void setType(LinkType type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Link [link=" + link + ", type=" + type + "]";
    }
}
