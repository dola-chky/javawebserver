package service;

import processor.RequestProcessor;
import utility.ApiUtility;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet("/sumApi")
public class RequestProcesorServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String payload = null;
        try {
            payload = ApiUtility.getRequestPayload(request);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }

        long sum=0;

        if("end".equalsIgnoreCase(payload)) {

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    RequestProcessor.notifyPendingRequests();
                }
            });
            executorService.shutdown();

            sum = RequestProcessor.waitForEnd(0);

        } else {
            Long requestValue = null;
            try {
                requestValue = ApiUtility.parsePayload(payload);
                sum = RequestProcessor.waitForEnd(requestValue);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
        }

        response.getWriter().write(String.valueOf(sum) + "\n");

    }
}
