package server;

import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import org.json.JSONException;

public class RequestGPT implements HttpHandler {
    Model model;

    public RequestGPT(Model model) {
        this.model = model;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("GET")) {
                response = handleGet(httpExchange);
            } else if (method.equals("POST")) {
                response = handlePost(httpExchange);
            } else {
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
    private String handleGet(HttpExchange httpExchange) throws IOException {
        String response = model.getRecipe();
        return response;
    }
    private String handlePost(HttpExchange httpExchange) throws IOException, JSONException, URISyntaxException, InterruptedException {
        String recipeTitle = model.getGPTRecipeTitle();
        String recipe = model.getGPTRecipe();
        String recipeData = recipeTitle + "@" + recipe;

        model.setRecipe(recipeData);
        String response = recipeData;
        return response;
    }
}