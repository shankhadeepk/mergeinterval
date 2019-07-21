package com.connect.proj.processor;

import com.connect.proj.AppConstant;
import com.connect.proj.model.Interval;
import com.connect.proj.model.Record;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.Optional;

public class FileProcessorI implements FileProcessor {
    @Override
    public void processFile(String fileName) {

        Reader in= null;
        Record record=null;

        ProcessDataService processDataService =null;
        try {
            in = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)));
            Iterable<CSVRecord> csvRecords= CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            Optional<CSVRecord> csvRecord1=null;

            for (CSVRecord csvRecord:csvRecords) {
                csvRecord1 = Optional.ofNullable(csvRecord);
                if (csvRecord1.isPresent()) {
                    record = new Record();
                    record.setTime(Integer.parseInt(csvRecord1.get().get(0)));
                    record.setStart(Integer.parseInt(csvRecord1.get().get(1)));
                    record.setEnd(Integer.parseInt(csvRecord1.get().get(2)));
                    record.setAction(csvRecord1.get().get(3));

                    System.out.println(record);
                    Interval interval=new Interval(record.getStart(),record.getEnd());
                    processDataService =new ProcessDataServiceI(7);

                    if(AppConstant.ADDED.getAction().equalsIgnoreCase(record.getAction()))
                        processDataService.addIntervals(interval);

                    if(AppConstant.REMOVED.getAction().equalsIgnoreCase(record.getAction()))
                        processDataService.removeIntervals(interval);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
