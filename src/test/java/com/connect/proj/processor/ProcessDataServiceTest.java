package com.connect.proj.processor;


import com.connect.proj.model.Interval;
import com.connect.proj.utils.IntervalCollection;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProcessDataServiceTest {

    @Test
    public void addIntervals() {
        Interval interval=new Interval();
        interval.setStart(1);
        interval.setEnd(20);
        ProcessDataService processDataService=new AdditionIntervals(7);
        assertTrue(processDataService.processSingleInterval(interval));
        IntervalCollection.clearIntervals();
    }

    @Test
    public void addEmptyInterval(){
        Interval interval=null;
        ProcessDataService processDataService=new AdditionIntervals(5);
        assertFalse(processDataService.processSingleInterval(interval));
    }

    @Test
    public void removeIntervals() {
        Interval interval=new Interval();
        interval.setStart(1);
        interval.setEnd(20);
        ProcessDataService processDataService=new AdditionIntervals(7);
        assertTrue(processDataService.processSingleInterval(interval));
        processDataService=new RemovalIntervals(7);
        assertTrue(processDataService.processSingleInterval(interval));
        IntervalCollection.clearIntervals();
    }

    @Test
    public void removeNonExistingInterval() {
        Interval interval=new Interval();
        interval.setStart(1);
        interval.setEnd(20);
        ProcessDataService processDataService=new AdditionIntervals(7);
        assertTrue(processDataService.processSingleInterval(interval));

        interval=new Interval();
        interval.setStart(21);
        interval.setEnd(30);
        processDataService=new RemovalIntervals(7);
        assertFalse(processDataService.processSingleInterval(interval));
        IntervalCollection.clearIntervals();
    }

    @Test
    public void removeEmptyInterval(){
        Interval interval=null;
        ProcessDataService processDataService=new RemovalIntervals(7);
        assertFalse(processDataService.processSingleInterval(interval));
    }

    @Test
    public void deleteInterval(){
        Interval interval=null;
        ProcessDataService processDataService=null;
        interval=new Interval();
        interval.setStart(1);
        interval.setEnd(6);

        processDataService=new AdditionIntervals(7);
        assertTrue(processDataService.processSingleInterval(interval));

        interval=new Interval();
        interval.setStart(5);
        interval.setEnd(7);

        assertTrue(processDataService.processSingleInterval(interval));

        processDataService=new DeletionIntervals(7);

        interval=new Interval();
        interval.setStart(2);
        interval.setEnd(3);

        assertTrue(processDataService.processSingleInterval(interval));
    }

    @Test
    public void readProblemStatementTestCasesAndProcess(){
     FileProcessor fileProcessor=new FileProcessorI();
     fileProcessor.processFile("problemStatementRecord.csv",7);
    }

    @Test
    public void removeAnotherIntervalAndCheck(){
        FileProcessor fileProcessor=new FileProcessorI();
        fileProcessor.processFile("record.csv",7);
    }

    @Test
    public void deleteIntervalAndCheck(){
        FileProcessor fileProcessor=new FileProcessorI();
        fileProcessor.processFile("deleteRecord.csv",7);
    }
}