package server;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import client.ChatGPT;
import client.Whisper;

import java.net.URISyntaxException;
 
public class Model {
    private Whisper useWhisper;
    private ChatGPT gpt;
    private Map<String, String> recipeData;
    
    // should be a place in mongoDB to store these before a recipe is officially added into the list
    File recording;
    String mealType;
    String transcription;
    String recipe;

    public Model() {
        this.useWhisper = new Whisper();
        this.recipeData = new HashMap<>();
        this.gpt = new ChatGPT();
    }
    // rename to useWHisperTranscription
    public String getWhisperTranscription() throws IOException, URISyntaxException {
        String transcription = useWhisper.transcribe(recording);
        return transcription;
    }
    public String getGPTRecipe() throws IOException, URISyntaxException, InterruptedException {
        // transcription is ingredients
        String recipe = gpt.generate(transcription, mealType);
        return recipe;
    }
    public String getGPTRecipeTitle() throws IOException, URISyntaxException, InterruptedException {
        // transcription is ingredients
        String recipeTitle = gpt.generateTitle(transcription, mealType);
        return recipeTitle;
    }
    public Map<String, String> getRecipeData() {
        return recipeData; 
    }
    public String getMealType() {
        return mealType;
    }
    public String getTranscription() {
        return transcription;
    }
    public String getRecipe() {
        return recipe;
    }

    public void setRecording(File recording) {
        this.recording = recording;
    }
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
//model should have the post, get methods

//put handle method here and make it have different if statements for different types of posts, in reality we only need one perform request in controller

/*
 * Requesthandler for recipeData(homescreen)
 * Requesthandler for mealType (promptscreen)
 */


