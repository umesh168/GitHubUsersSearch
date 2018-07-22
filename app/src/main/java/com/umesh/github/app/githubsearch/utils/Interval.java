package com.umesh.github.app.githubsearch.utils;

import java.util.Date;

/**
 * Created by Umesh on 17-09-2016.
 */
public class Interval {

    public static final int MILLI_TO_MINUTE = 1000 * 60;
    private double intervalStart;
    private double intervalEnd;
    private Date startDate;
    private Date endDate;

    private Interval() {

    }

    public Interval (double intervalStart, double intervalEnd) {
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
    }

    public Interval (Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /*public boolean contains(double val) {
        if (val >= intervalStart &&  val <= intervalEnd)
            return true;
        else return false;
    }*/

    public boolean contains(Date date) {
        if ((date.getTime() >= startDate.getTime()) &&  (date.getTime() <= endDate.getTime()))
            return true;
        else return false;
    }

    public double getIntervalStart() {
        return intervalStart;
    }

    public void setIntervalStart(double intervalStart) {
        this.intervalStart = intervalStart;
    }

    public double getIntervalEnd() {
        return intervalEnd;
    }

    public void setIntervalEnd(double intervalEnd) {
        this.intervalEnd = intervalEnd;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
