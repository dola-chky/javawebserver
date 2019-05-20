package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestProcessorData {
    private List<Long> requestValues = Collections.synchronizedList(new ArrayList<>());
    private long sum;

    public List<Long> getRequestValues() {
        return this.requestValues;
    }

    public void setRequestValues(List<Long> requestValues) {
        this.requestValues = requestValues;
    }

    public long getSum() {
        return this.sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public void clear() {
        this.requestValues.clear();
    }
}
