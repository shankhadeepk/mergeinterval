package com.connect.proj.utils;

import com.connect.proj.model.Interval;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class IntervalCollection {
    public static List<Interval> getIntervals() {
        return intervals;
    }

    public static List<Interval> getAlreadyTraversedIntervals() {
        return alreadyTraversedIntervals;
    }

    private static List<Interval> intervals=new CopyOnWriteArrayList<>();
    private static List<Interval> alreadyTraversedIntervals=new CopyOnWriteArrayList<>();

    public static void clearIntervals(){
        intervals.clear();
        alreadyTraversedIntervals.clear();
    }
}
