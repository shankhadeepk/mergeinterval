package com.connect.proj.processor;

import com.connect.proj.model.Interval;
import com.connect.proj.utils.IntervalCollection;

import java.util.*;

/*
*
* DeletionIntervals processes the intervals which needs to be deleted from existing intervals
*
*
* */
public class DeletionIntervals implements ProcessDataService {

    private int mergeDistance;

    public DeletionIntervals(int mergeDistance) {
        this.mergeDistance=mergeDistance;
    }

    @Override
    public boolean processSingleInterval(Interval newInterval) {
        Map<String,List<Interval>> changedIntervals=null;
        boolean flag=false;

        int start=0;
        int end=0;

        if(newInterval!=null) {
            if (IntervalCollection.getIntervals().size() == 0) {
                IntervalCollection.getIntervals().add(newInterval);
            } else {

                changedIntervals = checkIntervalAndDelete(newInterval);

                if (changedIntervals != null) {

                    IntervalCollection.getIntervals().removeAll(changedIntervals.get("E"));
                    IntervalCollection.getIntervals().addAll(changedIntervals.get("U"));
                } else {
                    return false;
                }
            }
        }else {
            return false;
        }

        System.out.println("Intervals as :");
        IntervalCollection.getIntervals().stream().forEach(System.out::println);
        return true;
    }
    private Map<String, List<Interval>> checkIntervalAndDelete(Interval newInterval) {

        Map<String, List<Interval>> resultantIntervals=null;
        Iterator<Interval> intervalIterator=null;
        Interval updatedInterval=null;
        List<Interval> updatedIntervals=null;
        List<Interval> existingIntervals=null;

        for(intervalIterator=IntervalCollection.getIntervals().iterator();intervalIterator.hasNext();) {
            Interval existingInterval=intervalIterator.next();

            if((newInterval.getStart() >= existingInterval.getStart()) && (newInterval.getEnd() <= existingInterval.getEnd())){

                updatedIntervals=new ArrayList<>();
                updatedInterval=new Interval();
                updatedInterval.setStart(existingInterval.getStart());
                updatedInterval.setEnd(newInterval.getStart());

                updatedIntervals.add(updatedInterval);

                updatedInterval=new Interval();
                updatedInterval.setStart(newInterval.getEnd());
                updatedInterval.setEnd(existingInterval.getEnd());

                updatedIntervals.add(updatedInterval);


                existingIntervals=new ArrayList<>();
                existingIntervals.add(existingInterval);

                resultantIntervals=new HashMap<>();
                resultantIntervals.put("U",updatedIntervals);
                resultantIntervals.put("E",existingIntervals);

                return resultantIntervals;
            }
        }
        return null;
    }
}
