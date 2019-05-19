package utility;

import java.util.List;
import java.util.Map;

public class CalculateSum {
    public static long getSum(List<Map<String, Object>> inputList){
        long sum = 0;
        for(Map<String, Object> inpMap:inputList){
            String userMessage = inpMap.get("userMessage").toString();
            long messageLongValue = Long.parseLong(userMessage);
            sum = sum + messageLongValue;
        }
        return sum;
    }
}
