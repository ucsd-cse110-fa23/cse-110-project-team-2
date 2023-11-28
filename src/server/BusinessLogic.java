package server;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

import client.ChatGPT;
import client.Whisper;

public class BusinessLogic {
    private ChatGPT cgpt;
    private Whisper wspr;
    public BusinessLogic(){
        this.cgpt = new ChatGPT();
        this.wspr = new Whisper();
    }
    //Generates using chatgpt
    public String generate(String ingredients, String mealtype) throws IOException, InterruptedException, URISyntaxException{
        return cgpt.generate(ingredients, mealtype);
    }

    public String generateTitle(String ingredients, String mealtype) throws IOException, InterruptedException, URISyntaxException{
        return cgpt.generateTitle(ingredients, mealtype);
    }
    //Transcribes using whisper
    public String transcribe(File recording) throws IOException, URISyntaxException, JSONException {
        return wspr.transcribe(recording);
    }

}
