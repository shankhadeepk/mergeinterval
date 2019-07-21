package com.connect.proj.processor;

import com.connect.proj.model.Interval;

public interface ProcessDataService {

    public boolean addIntervals(Interval newInterval);
    public boolean removeIntervals(Interval newInterval);
    public void clearIntervals();
}
