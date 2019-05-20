package utility;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class ApiUtility {
    public static String getRequestPayload(HttpServletRequest request) throws Exception {
        final StringBuilder stringBuilder = new StringBuilder();
        final BufferedReader reader = request.getReader();

        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        if(stringBuilder == null || stringBuilder.length() == 0) {
            throw new Exception("Invalid request body");
        }

        return stringBuilder.toString().trim();
    }

    public static Long parsePayload(String payload) throws Exception {
        Long value;
        try {
            value = Long.parseLong(payload);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid request body", e);
        }
        return value;
    }
}
