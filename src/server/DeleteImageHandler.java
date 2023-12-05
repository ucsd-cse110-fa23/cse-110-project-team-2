package server;

import java.net.URISyntaxException;
import com.sun.net.httpserver.*;

import javafx.scene.shape.Path;
import java.nio.file.Paths;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DeleteImageHandler implements HttpHandler {

    public DeleteImageHandler() {
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("DELETE")) {
                response = handleDelete(httpExchange);
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

    private String handleDelete(HttpExchange httpExchange) throws IOException, InterruptedException, URISyntaxException{
        InputStream inStream = httpExchange.getRequestBody();
        String response = new String(inStream.readAllBytes(), StandardCharsets.UTF_8);

        Path image = (Path) Paths.get(response + ".png");
        File f = ((java.nio.file.Path) image).toFile();
        
        if (f.exists()) {
            f.delete();
        }
        return "true";
    }
}
