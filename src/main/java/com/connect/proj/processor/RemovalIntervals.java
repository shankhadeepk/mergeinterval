package com.connect.proj.processor;

import com.connect.proj.model.Interval;
import com.connect.proj.utils.IntervalCollection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/*
 *
 * RemovalIntervals processes the intervals which needs to be removed from existing intervals
 *
 *
 * */
public class RemovalIntervals implements ProcessDataService {

    private int mergeDistance;

    public RemovalIntervals(int mergeDistance) {
        this.mergeDistance=mergeDistance;
    }

    @Override
    public boolean processSingleInterval(Interval newInterval) {
        Map<String,Interval> changedInterval=null;
        boolean flag=false;

        int start=0;
        int end=0;

        if(newInterval!=null) {
            if (IntervalCollection.getIntervals().size() == 0) {
                IntervalCollection.getIntervals().add(newInterval);
            } else {

                changedInterval = checkIntervalAndRemove(newInterval);

                if (changedInterval != null) {
                    IntervalCollection.getIntervals().remove(changedInterval.get("E"));
                    IntervalCollection.getIntervals().add(changedInterval.get("U"));
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
    private Map<String, Interval> checkIntervalAndRemove(Interval newInterval) {

        Map<String,Interval> resultantIntervals=null;
        Iterator<Interval> intervalIterator=null;
        Interval updatedInterval=null;

        for(intervalIterator=IntervalCollection.getIntervals().iterator();intervalIterator.hasNext();) {
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
        for(int i=IntervalCollection.getAlreadyTraversedIntervals().size() - 1;i >= 0; i--){

            preInterval=IntervalCollection.getAlreadyTraversedIntervals().get(i);

            if((existingInterval.getStart() <= preInterval.getStart()) && (existingInterval.getEnd() >= preInterval.getEnd())) {
                return preInterval;
            }
        }
        return null;
    }
}
