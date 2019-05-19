import com.google.gson.Gson;
import utility.CalculateSum;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/sumApi")
public class RequestProcesorServlet extends HttpServlet {

    private Gson gson = new Gson();
    List<Map<String, Object>> inputList = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();

        Map<String, Object> responseMap = new HashMap<>();

        Map<String, Object> inputMap = gson.fromJson(data, Map.class);
        if(inputMap.get("userMessage").equals("end")){
            long sum = CalculateSum.getSum(inputList);
            responseMap.put("status","completed");
            responseMap.put("result", sum);
            inputList.clear();
        }else{
            inputList.add(inputMap);
            responseMap.put("status","pending");
        }

        //responseMap.put("userIp", request.getRemoteUser());
        PrintWriter out = response.getWriter();
        String jsonStr = this.gson.toJson(responseMap);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonStr);
        out.flush();
    }
}
