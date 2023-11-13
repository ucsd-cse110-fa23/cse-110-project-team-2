// package client;

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.net.URI;
// import java.net.URISyntaxException;
 

// public class Model {
//     private ChatGPT recipeMaker = new ChatGPT();
//     private Whisper transcriber = new Whisper();
    
//     public String generateRecipe(String ingredients, String mealType) throws IOException, InterruptedException, URISyntaxException{
//         return recipeMaker.generate(ingredients, mealType);
//     }

//     public String generateTitle(String ingredients, String mealType) throws IOException, InterruptedException, URISyntaxException{
//         return recipeMaker.generateTitle(ingredients, mealType);
//     }
//     public String transcribe(File recording) throws IOException, URISyntaxException, JSONException {
//         return transcriber.transcribe(recording);
//     }
// }

package client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.URI;
 

public class Model {
    public String performRequest(String method, String language, String query) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/";
            if (query != null) {
                urlString += "?=" + query;
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

