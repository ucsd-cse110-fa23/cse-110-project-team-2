package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.JSONException;

import com.sun.net.httpserver.*;

public class RequestMeal implements HttpHandler {
    Model model;

    public RequestMeal(Model model) {
        this.model = model;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();
        //Boolean valid;

        try {
            if (method.equals("GET")) {
                response = handleGet(httpExchange);
            } else if (method.equals("POST")) {
                response = handlePost(httpExchange);
                // Check if response is a valid option e.i. breakfast, lunch, dinner
            } else {
                throw new Exception("Not Valid Request Method");
            }
        } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
    }

    private String handleGet(HttpExchange httpExchange) throws IOException {
        String response = model.getMealType();
        return response;
    }

    private String handlePost(HttpExchange httpExchange) throws IOException, JSONException, URISyntaxException {
        //you're assuming you have access to the recording already
        String transcription = model.getWhisperTranscription();
        model.setMealType(transcription);
        String response = "false";
        if (model.getMealType().equals("Breakfast") || model.getMealType().equals("Lunch") || 
        model.getMealType().equals("Dinner")) {
            response = "valid meal type";
            return response;
        }
        return response;
    }
    
}
