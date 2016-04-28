package com.pfwu.ttmj.core.datatypes;

import java.io.Serializable;
import java.util.List;

public class DownloadEntry implements Serializable {
    private String name;
    private List<Link> downloadLinks;
    private int sizeInMegaBytes;
    private String subtitle;
    private String format;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Link> getDownloadLinks() {
        return downloadLinks;
    }

    public void setDownloadLinks(List<Link> downloadLinks) {
        this.downloadLinks = downloadLinks;
    }

    public int getSizeInMegaBytes() {
        return sizeInMegaBytes;
    }

    public void setSizeInMegaBytes(int sizeInMegaBytes) {
        this.sizeInMegaBytes = sizeInMegaBytes;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "DownloadEntry [name=" + name + ", downloadLinks=" + downloadLinks + ", sizeInMegaBytes="
                + sizeInMegaBytes + ", subtitle=" + subtitle + ", format=" + format + "]";
    }

}
