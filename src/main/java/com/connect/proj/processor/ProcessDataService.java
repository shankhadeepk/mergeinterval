package com.connect.proj.processor;

import com.connect.proj.model.Interval;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public interface ProcessDataService {

    public void addIntervals(Interval newInterval);
    public void removeIntervals(Interval newInterval);
    public Map<String, Interval> checkIntervalAndRemove(Interval newInterval);
    public Interval getExactPreviousInterval(Interval existingInterval);
    public Map<String,Interval> mergeTwoIntervals(Interval newInterval);
    public void clearIntervals();
}
