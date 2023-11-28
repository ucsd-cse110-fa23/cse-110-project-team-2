package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class GPTHandler implements HttpHandler{

    BusinessLogic bl;

    public GPTHandler(BusinessLogic bl){
        this.bl = bl;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("POST")) {
                response = handlePost(httpExchange);
            }
            else {
                throw new Exception("Not Valid Request Method");
            }
        } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }
        // Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
    }
    private String handlePost(HttpExchange httpExchange) throws IOException, InterruptedException, URISyntaxException{
        InputStream inStream = httpExchange.getRequestBody();
        String requestBody = new String(inStream.readAllBytes(), StandardCharsets.UTF_8);
        JSONObject requestJson = new JSONObject(requestBody);
        String ingredients = requestJson.getString("ingredients");
        String mealtype = requestJson.getString("type");
        String response = bl.generate(ingredients, mealtype);
        return response;
    }
}
