package server;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

import client.ChatGPT;
import client.Whisper;

public class BusinessLogic {
    private ChatGPT cgpt;
    private Whisper wspr;
    //private HashMap<String,String> accounts;
    private Accounts accounts;
    public BusinessLogic(){
        this.cgpt = new ChatGPT();
        this.wspr = new Whisper();
        //this.accounts = new HashMap<String,String>();
        this.accounts = new Accounts();
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

    public boolean checkLogin(String username, String password){
        return accounts.checkLogin(username, password);
    }

}
