package com.connect.proj.processor;

import com.connect.proj.model.Interval;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public interface ProcessDataService {

    public boolean addIntervals(Interval newInterval);
    public boolean removeIntervals(Interval newInterval);
    public void clearIntervals();
}
