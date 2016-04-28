package com.pfwu.ttmj.core.datatypes;

import java.util.Date;

/**
 * Created by pfwu on 4/27/2016.
 */
public class DramaDetail {
    private String name;
    private String showDay;
    private String status;
    private Date publishTime;
    private Date lastUpdateTime;
    private Date returnDate;
    private String coverImageURL;

    public String getCoverImageURL() {
        return coverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        this.coverImageURL = coverImageURL;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShowDay() {
        return showDay;
    }

    public void setShowDay(String showDay) {
        this.showDay = showDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DramaDetail{" +
                "name='" + name + '\'' +
                ", showDay='" + showDay + '\'' +
                ", status='" + status + '\'' +
                ", publishTime=" + publishTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", returnDate=" + returnDate +
                ", coverImageURL='" + coverImageURL + '\'' +
                '}';
    }
}
