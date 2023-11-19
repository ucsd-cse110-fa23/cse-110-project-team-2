package client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;
 
public class Model {
    private Whisper useWhisper;
    private Map<String, String> recipeData;
    // will be used when user says meal type and their ingredients
    File recording;
    String mealType;

    public Model() {
        useWhisper = new Whisper();
        recipeData = new HashMap<>();
    }

    public String getWhisperTranscription() throws JSONException, IOException, URISyntaxException {
        String transcription = useWhisper.transcribe(recording);
        return transcription;
    }
    public Map<String, String> getRecipeData() {
        return recipeData;
    }
    public String getMealType() {
        return mealType;
    }

    public void setRecording(File recording) {
        this.recording = recording;
    }
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
}

/*
 * Requesthandler for recipeData(homescreen)
 * Requesthandler for mealType (promptscreen)
 */


