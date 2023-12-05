package server;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import client.ChatGPT;
import client.Whisper;

public class BusinessLogic {
    private ChatGPT cgpt;
    private Whisper wspr;
<<<<<<< HEAD
    //private HashMap<String,String> accounts;
    private Accounts accounts;
    public BusinessLogic(){
        this.cgpt = new ChatGPT();
        this.wspr = new Whisper();
        //this.accounts = new HashMap<String,String>();
        this.accounts = new Accounts();
=======
    private DallE dall;
    private HashMap<String,String> accounts;
    public BusinessLogic(){
        this.cgpt = new ChatGPT();
        this.wspr = new Whisper();
        this.dall = new DallE();
        this.accounts = new HashMap<String,String>();
>>>>>>> 0bf28738082f4f8f283dc12a1dc72243331035ed
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

    public String generateImage(String title) throws IOException, URISyntaxException, JSONException {
        return dall.image(title);
    }

    public boolean checkLogin(String username, String password){
        return accounts.checkLogin(username, password);
    }

    public boolean addUserAccount(String username, String password) throws IOException{
        return accounts.addUserAccount(username, password);
    }

    public boolean deleteRecipe(String username, String recipeTitleDate){
        return accounts.deleteRecipeFromAccount(username, recipeTitleDate);
    }

    public JSONObject getAllRecipes(String username){
        return accounts.getUserRecipes(username);
    }

}
