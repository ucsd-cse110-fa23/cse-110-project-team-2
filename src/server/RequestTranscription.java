package server;

import com.sun.net.httpserver.*;

import client.Model;
import javafx.scene.shape.Path;

import java.io.*;
import java.net.*;
import java.util.*;

import org.json.JSONException;

public class RequestTranscription implements HttpHandler {
    private final Model model;
    private String transcription;

    public RequestTranscription(Model model) {
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
        String response = "Invalid GET request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        if (query != null) {
            String value = query.substring(query.indexOf("=") + 1);
            String year = data.get(value); // Retrieve data from hashmap
            if (year != null) {
                response = year;
                System.out.println("Queried for " + value + " and found " + year);
            } else {
                response = "No data found for " + value;
            }
        }
        return response;
    }

    private String handlePost(HttpExchange httpExchange) throws IOException, JSONException, URISyntaxException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String postData = scanner.nextLine();
        String recording = postData;

        //will have to implement getRecipeType for now just use dummy variable
        transcription = model.getWhisperTranscription(recording);//if we use correct input for recording then we won't get throw errors

        String response = transcription;
        scanner.close();

        return response;
    }
}