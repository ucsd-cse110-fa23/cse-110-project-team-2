package client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.URI;
 

public class RequestSender {
    public String performRequest(String method, String language, String query) {//this thing is writing into the server to the corresponding handler
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/";
            if (query != null) {
                urlString += "?=" + query;//make sure you replace all spaces with underscores when you add to the urlstring
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("POST") || method.equals("PUT")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(language);
                out.flush();
                out.close();
            }

            InputStream in = conn.getInputStream();
            String response = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
}

