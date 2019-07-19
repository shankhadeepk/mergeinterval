package com.connect.proj.processor;


import com.connect.proj.model.Interval;
import org.junit.Test;

public class ProcessDataServiceTest {

    @Test
    public void addIntervals() {
        Interval interval=new Interval();
        interval.setStart(1);
        interval.setEnd(20);
        ProcessDataService processDataService=new ProcessDataServiceI(7);
        processDataService.addIntervals(interval);
    }

    @Test
    public void removeIntervals() {
        Interval interval=new Interval();
        interval.setStart(1);
        interval.setEnd(20);
        ProcessDataService processDataService=new ProcessDataServiceI(7);
        processDataService.addIntervals(interval);
        processDataService.removeIntervals(interval);
    }

    @Test
    public void checkIntervalAndRemove() {
    }

    @Test
    public void getExactPreviousInterval() {
    }

    @Test
    public void mergeTwoIntervals() {
    }
}