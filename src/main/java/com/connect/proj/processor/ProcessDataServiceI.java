package com.connect.proj.processor;

import com.connect.proj.model.Interval;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProcessDataServiceI implements ProcessDataService{

    private int mergeDistance;

    public ProcessDataServiceI(int mergeDistance){
        this.mergeDistance=mergeDistance;
    }

    private static List<Interval> intervals=new CopyOnWriteArrayList<>();
    private static List<Interval> alreadyTraversedIntervals=new CopyOnWriteArrayList<>();

    public void clearIntervals(){
         intervals.clear();
         alreadyTraversedIntervals.clear();
    }

    public boolean addIntervals(Interval newInterval){
        Map<String,Interval> addedInterval=null;
        boolean flag=false;

        int start=0;
        int end=0;

        if(newInterval!=null) {
            if (intervals.size() == 0) {
                intervals.add(newInterval);
            } else {

                addedInterval = mergeTwoIntervals(newInterval);

                if (addedInterval != null) {
                    intervals.remove(addedInterval.get("E"));
                    intervals.add(addedInterval.get("U"));
                } else {
                    intervals.add(newInterval);
                }
            }
        }else {
            return false;
        }

        System.out.println("Intervals as :");
        intervals.stream().forEach(System.out::println);

        return true;
    }
    public boolean removeIntervals(Interval newInterval){
        Map<String,Interval> changedInterval=null;
        boolean flag=false;

        int start=0;
        int end=0;

        if(newInterval!=null) {
            if (intervals.size() == 0) {
                intervals.add(newInterval);
            } else {

                changedInterval = checkIntervalAndRemove(newInterval);

                if (changedInterval != null) {
                    intervals.remove(changedInterval.get("E"));
                    intervals.add(changedInterval.get("U"));
                } else {
                    return false;
                }
            }
        }else {
            return false;
        }

        System.out.println("Intervals as :");
        intervals.stream().forEach(System.out::println);
        return true;
    }

    private Map<String, Interval> checkIntervalAndRemove(Interval newInterval) {

        Map<String,Interval> resultantIntervals=null;
        Iterator<Interval> intervalIterator=null;
        Interval updatedInterval=null;

        for(intervalIterator=intervals.iterator();intervalIterator.hasNext();) {
            Interval existingInterval=intervalIterator.next();

            if((newInterval.getStart() >= existingInterval.getStart()) && (newInterval.getEnd() <= existingInterval.getEnd())){

                Interval previousInterval=getExactPreviousInterval(existingInterval);

                if(previousInterval!=null) {
                    updatedInterval=new Interval();
                    updatedInterval.setStart(previousInterval.getStart());

                    if (existingInterval.getEnd() == newInterval.getEnd()) {
                        updatedInterval.setEnd(previousInterval.getEnd());
                    } else {
                        updatedInterval.setEnd(existingInterval.getEnd());
                    }
                }

                resultantIntervals=new HashMap<>();
                resultantIntervals.put("U",updatedInterval);
                resultantIntervals.put("E",existingInterval);

                return resultantIntervals;
            }
        }
        return null;
    }

    private Interval getExactPreviousInterval(Interval existingInterval) {
        Interval preInterval=null;
        for(int i=alreadyTraversedIntervals.size() - 1;i >= 0; i--){

            preInterval=alreadyTraversedIntervals.get(i);

            if((existingInterval.getStart() <= preInterval.getStart()) && (existingInterval.getEnd() >= preInterval.getEnd())) {
                return preInterval;
            }
        }
        return null;
    }

    private Map<String,Interval> mergeTwoIntervals(Interval newInterval){

        alreadyTraversedIntervals.add(newInterval);

        Map<String,Interval> resultantIntervals=null;
        Iterator<Interval> intervalIterator=null;
        Interval updatedInterval =null;


        for(intervalIterator=intervals.iterator();intervalIterator.hasNext();) {
            Interval existingInterval=intervalIterator.next();
            if ((Math.abs(newInterval.getEnd() - existingInterval.getStart()) <= this.mergeDistance)
                    || (Math.abs(existingInterval.getStart() - newInterval.getStart()) <= this.mergeDistance)
                    || (Math.abs(existingInterval.getEnd() - newInterval.getStart()) <= this.mergeDistance)
                    || ((existingInterval.getStart() < newInterval.getStart()) && (existingInterval.getEnd() > newInterval.getEnd()))) {

                updatedInterval = new Interval();

                if (newInterval.getStart() > existingInterval.getStart()) {
                    updatedInterval.setStart(existingInterval.getStart());
                } else {
                    updatedInterval.setStart(newInterval.getStart());
                }

                if (newInterval.getEnd() > existingInterval.getEnd()) {
                    updatedInterval.setEnd(newInterval.getEnd());
                } else {
                    updatedInterval.setEnd(existingInterval.getEnd());
                }
                resultantIntervals=new HashMap<>();
                resultantIntervals.put("U",updatedInterval);
                resultantIntervals.put("E",existingInterval);
                return resultantIntervals;
            }
        }
        return null;
    }
}
