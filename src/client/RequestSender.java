package client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
 

public class RequestSender {
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

    public String performTranscribe(File recording){
        //Code taken from ChatGPT 3.5
        //Prompt used; write me code to send a file to this server using java
        //Done after the prompt used in WhisperHandler
        try{
            String serverUrl = "http://localhost:8100/transcribe";
            if (!recording.exists()) {
                throw new FileNotFoundException("File Not Found");
            }
    
            URL url = new URI(serverUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            connection.setRequestProperty("Content-Disposition", "attachment; filename=\"" + recording.getName() + "\"");
    
            OutputStream outputStream = connection.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(recording);
    
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
    
            outputStream.flush();
            outputStream.close();
            fileInputStream.close();
    
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Reading the response from the server
                String response = new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                connection.disconnect();
                return response;
            } else {
                connection.disconnect();
                return "File upload failed with response code: " + responseCode;
            }   
        } catch (Exception e){
            e.printStackTrace();
            return "Error: File Not Found";
        }
    }
    public String performGenerateRecipe(String ingredients, String mealtype) throws IOException, InterruptedException {
            String urlString = "http://localhost:8100/generate";
            // Create a request body which you will pass into request object
            JSONObject requestBody = new JSONObject();
            requestBody.put("ingredients", ingredients);
            requestBody.put("type", mealtype);

            // Create the HTTP Client
            HttpClient client = HttpClient.newHttpClient();

            URI ur = URI.create(urlString);
            // Create the request object
            HttpRequest request = HttpRequest
            .newBuilder()
            .uri(ur)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
            .build();

            // Send the request and receive the response
            HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
            );
            
            // Process the response
            String responseBody = response.body();
            //System.out.println(responseBody);
            return responseBody;
    }
    
    public String performLogin(String username, String password){
        String urlString = "http://localhost:8100/login";
        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("user", username);
        requestBody.put("pw", password);

        try{
            // Create the HTTP Client
            HttpClient client = HttpClient.newHttpClient();

            URI ur = URI.create(urlString);
            // Create the request object
            HttpRequest request = HttpRequest
            .newBuilder()
            .uri(ur)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
            .build();

            // Send the request and receive the response
            HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString());
            // Process the response
            String responseBody = response.body();
            //System.out.println(responseBody);
            //response will be "true" upon valid login, and error message to be displayed upon invalid login
            return responseBody;
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "Error";
        }
    }

    public String performCreateAccount(String username, String password){
        String urlString = "http://localhost:8100/createAccount";
        JSONObject requestBody = new JSONObject();
        requestBody.put("user", username);
        requestBody.put("pw", password);

        try{
            // Create the HTTP Client
            HttpClient client = HttpClient.newHttpClient();

            URI ur = URI.create(urlString);
            // Create the request object
            HttpRequest request = HttpRequest
            .newBuilder()
            .uri(ur)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
            .build();

            // Send the request and receive the response
            HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString());
            // Process the response
            String responseBody = response.body();
            //System.out.println(responseBody);
            //response will be "true" upon valid account creation, and error message to be displayed upon invalid creation
            return responseBody;
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "Error";
        }
    }

    public String performDeleteRecipe(String username, String recipeTitleDate) throws IOException, InterruptedException{
        String urlString = "http://localhost:8100/deleteRecipe";
            // Create a request body which you will pass into request object
            JSONObject requestBody = new JSONObject();
            requestBody.put("username", username);
            requestBody.put("titleDate", recipeTitleDate);

            // Create the HTTP Client
            HttpClient client = HttpClient.newHttpClient();

            URI ur = URI.create(urlString);
            // Create the request object
            HttpRequest request = HttpRequest
            .newBuilder()
            .uri(ur)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
            .build();

            // Send the request and receive the response
            HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
            );
            
            // Process the response
            String responseBody = response.body();
            //System.out.println(responseBody);
            return responseBody;
    }
}

