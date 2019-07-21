package com.connect.proj.model;

/*
* The Record class holds record as read from the csv files
*
*
* */
public class Record {

    private int time;
    private int start;
    private int end;
    private String action;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Record{" +
                "time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", action='" + action + '\'' +
                '}';
    }
}
