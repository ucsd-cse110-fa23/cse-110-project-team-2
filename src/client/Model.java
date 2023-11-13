package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
 

public class Model {
    private ChatGPT recipeMaker = new ChatGPT();
    private Whisper transcriber = new Whisper();
    
    public String generateRecipe(String ingredients, String mealType) throws IOException, InterruptedException, URISyntaxException{
        return recipeMaker.generate(ingredients, mealType);
    }

    public String generateTitle(String ingredients, String mealType) throws IOException, InterruptedException, URISyntaxException{
        return recipeMaker.generateTitle(ingredients, mealType);
    }
    public String transcribe(File recording) throws IOException, URISyntaxException, JSONException {
        return transcriber.transcribe(recording);
    }
}