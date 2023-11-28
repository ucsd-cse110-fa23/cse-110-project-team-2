package server;

import com.sun.net.httpserver.*;

import javafx.scene.shape.Path;

import java.io.*;
import java.net.*;
import java.util.*;

import org.json.JSONException;

public class RequestTranscription implements HttpHandler {
    Model model;

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
        String response = model.getTranscription();
        return response;
    }

    private String handlePost(HttpExchange httpExchange) throws IOException, URISyntaxException {
        String transcription = model.getWhisperTranscription();
        model.setTranscription(transcription);
        
        String response = transcription;
        return response;
    }
}