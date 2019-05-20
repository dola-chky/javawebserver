package processor;

import model.RequestProcessorData;

public class RequestProcessor {
    private static RequestProcessorData requestProcessorData = new RequestProcessorData();

    public static long waitForEnd(long requestValue) {
        requestProcessorData.getRequestValues().add(requestValue);

        synchronized (requestProcessorData) {
            try {
                requestProcessorData.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return requestProcessorData.getSum();
    }

    public static void notifyPendingRequests() {
        synchronized (requestProcessorData) {
            long summation = 0;
            for (long requestValue : requestProcessorData.getRequestValues()) {
                summation += requestValue;
            }
            requestProcessorData.setSum(summation);
            requestProcessorData.clear();
            requestProcessorData.notifyAll();
        }
    }
}
