package test.java;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

import main.java.WhisperInterface;

public class mockWhisper implements WhisperInterface{
    @Override
    public String transcribe(File recording) throws IOException, URISyntaxException, JSONException {
        if(recording.exists()){
            return "Working";
        }
        return "No file found";
    }
    
}