import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class CalculatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder jsonRequest = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            jsonRequest.append(line);
        }


        JSONObject jsonObject = new JSONObject(jsonRequest.toString());

        // Извлекаем параметры из JSON
        double a = jsonObject.getDouble("a");
        double b = jsonObject.getDouble("b");
        String mathOperation = jsonObject.getString("math");


        double result = 0;
        switch (mathOperation) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b != 0) {
                    result = a / b;
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
        }


        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("result", result);


        response.setContentType("application/json");


        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }
}