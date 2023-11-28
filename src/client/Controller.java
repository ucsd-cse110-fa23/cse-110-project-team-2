package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Controller {
    // Automate this process to reduce lines of code.
    public String performRequestMeal(String method, String mealType) {
        try {
            String urlString = "http://localhost:8100/meal";
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("POST")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(mealType);
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
    public String performRequestRecording(String method, File recording) {
        //Code taken from ChatGPT 3.5
        //Prompt used; write me code to send a file to this server using java
        //Done after the prompt used in WhisperHandler
        try{
            String serverUrl = "http://localhost:8100/recording";
            if (!recording.exists()) {
                throw new FileNotFoundException("File Not Found");
            }
    
            URL url = new URI(serverUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    
            connection.setRequestMethod(method);
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
    public String performRequestTranscription(String method) {
        try {
            String urlString = "http://localhost:8100/transcription";
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
    public String performRequestGPT(String method) {
        try {
            String urlString = "http://localhost:8100/gpt";
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            InputStream in = conn.getInputStream();
            String response = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
    public String performRequestRecipe(String method, String language, String year, String query) {
        try {
            String urlString = "http://localhost:8100/recipe";
            if (query != null) {
                urlString += query;
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("POST") || method.equals("PUT")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(language + "," + year);
                out.flush();
                out.close();
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }   
}
//response codes code:
//int responseCode = ((HttpURLConnection) connection).getResponseCode();
//System.out.println("Response code: [" + responseCode + "]");