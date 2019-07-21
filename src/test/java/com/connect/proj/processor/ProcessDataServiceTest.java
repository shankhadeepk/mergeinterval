package com.connect.proj.processor;


import com.connect.proj.AppConstant;
import com.connect.proj.model.Interval;
import com.connect.proj.model.Record;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.*;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProcessDataServiceTest {

    @Test
    public void addIntervals() {
        Interval interval=new Interval();
        interval.setStart(1);
        interval.setEnd(20);
        ProcessDataService processDataService=new ProcessDataServiceI(7);
        assertTrue(processDataService.addIntervals(interval));
        processDataService.clearIntervals();
    }

    @Test
    public void addEmptyInterval(){
        Interval interval=null;
        ProcessDataService processDataService=new ProcessDataServiceI(5);
        assertFalse(processDataService.addIntervals(interval));
    }

    @Test
    public void removeIntervals() {
        Interval interval=new Interval();
        interval.setStart(1);
        interval.setEnd(20);
        ProcessDataService processDataService=new ProcessDataServiceI(7);
        assertTrue(processDataService.addIntervals(interval));
        assertTrue(processDataService.removeIntervals(interval));
        processDataService.clearIntervals();
    }

    @Test
    public void removeNonExistingInterval() {
        Interval interval=new Interval();
        interval.setStart(1);
        interval.setEnd(20);
        ProcessDataService processDataService=new ProcessDataServiceI(7);
        assertTrue(processDataService.addIntervals(interval));

        interval=new Interval();
        interval.setStart(21);
        interval.setEnd(30);
        assertFalse(processDataService.removeIntervals(interval));
        processDataService.clearIntervals();
    }

    @Test
    public void removeEmptyInterval(){
        Interval interval=null;
        ProcessDataService processDataService=new ProcessDataServiceI(7);
        assertFalse(processDataService.removeIntervals(interval));
    }

    @Test
    public void readProblemStatementTestCasesAndProcess(){
     FileProcessor fileProcessor=new FileProcessorI();
     fileProcessor.processFile("problemStatementRecord.csv");
    }

    @Test
    public void removeAnotherIntervalAndCheck(){
        FileProcessor fileProcessor=new FileProcessorI();
        fileProcessor.processFile("record.csv");
    }
}