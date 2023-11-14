package client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;
 
public class Model {
    private Whisper useWhisper = new Whisper();

    public String getWhisperTranscription(String recording) throws JSONException, IOException, URISyntaxException {
        Path recordingPath = Paths.get(recording);
        String transcription = useWhisper.transcribe(recordingPath.toFile());

        return transcription;
    }
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
    public String performRequestRecording(String method, String recording) {
        // Implement your HTTP request logic here and return the response
        try {
            String urlString = "http://localhost:8100/recording";
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("POST")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(recording);
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
    public String performRequestTranscription(String method, String recording) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/transcription";
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("POST")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(recording);
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
    public String performRequestRecipe(String method, String language, String year, String query) {
        // Implement your HTTP request logic here and return the response

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



