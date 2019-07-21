package com.connect.proj.processor;

import com.connect.proj.model.Interval;
import com.connect.proj.utils.IntervalCollection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
* AdditionIntervals is implementation for intervals which needs to be added to the list
*
*
* */
public class AdditionIntervals implements ProcessDataService {

    private int mergeDistance;

    public AdditionIntervals(int mergeDistance) {
        this.mergeDistance=mergeDistance;
    }

    @Override
    public boolean processSingleInterval(Interval newInterval) {
        Map<String,Interval> addedInterval=null;
        boolean flag=false;

        int start=0;
        int end=0;

        if(newInterval!=null) {
            if (IntervalCollection.getIntervals().size() == 0) {
                IntervalCollection.getIntervals().add(newInterval);
            } else {

                addedInterval = mergeTwoIntervals(newInterval);

                if (addedInterval != null) {
                    IntervalCollection.getIntervals().remove(addedInterval.get("E"));
                    IntervalCollection.getIntervals().add(addedInterval.get("U"));
                } else {
                    IntervalCollection.getIntervals().add(newInterval);
                }
            }
        }else {
            return false;
        }

        System.out.println("Intervals as :");
        IntervalCollection.getIntervals().stream().forEach(System.out::println);

        return true;
    }
    private Map<String,Interval> mergeTwoIntervals(Interval newInterval){

        IntervalCollection.getAlreadyTraversedIntervals().add(newInterval);

        Map<String,Interval> resultantIntervals=null;
        Iterator<Interval> intervalIterator=null;
        Interval updatedInterval =null;


        for(intervalIterator=IntervalCollection.getIntervals().iterator();intervalIterator.hasNext();) {
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
