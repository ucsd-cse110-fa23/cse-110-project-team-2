package main.java;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class RecordScreen extends BorderPane {
    private Header header;
    private BackFooter footer;
    private Button recordButton;
    private String recipeType;
    private Boolean isRecording = false;
    private Recorder recorder;
    private Whisper testWhisper;

    RecordScreen(String type) {
        recipeType = type;
        header = new Header("What ingredients do you have now? You said you wanted: " + getRecipeType());
        footer = new BackFooter();
        recordButton = new Button("Record");
        recordButton.setStyle("-fx-background-color: #43ED58");
        recorder = new Recorder();
        testWhisper = new Whisper();
        this.setTop(header);
        this.setCenter(recordButton);
        this.setBottom(footer);
        addListeners();
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void toggleRecord() throws JSONException, IOException, URISyntaxException {
        isRecording = !isRecording;
        if(isRecording) {
            recordButton.setText("Recording...");
            recordButton.setStyle("-fx-background-color: #eb6f5b;");
            recorder.startRecording();
        }
        else {
            recordButton.setText("Record");
            recordButton.setStyle("-fx-background-color: #43ED58;");
            recorder.stopRecording();
            Path recording = Paths.get("./recording.wav");
            moveToNextScreen(testWhisper.transcribe(recording.toFile()), getRecipeType());
            // moveToNextScreen("I have chicken and waffles.", getRecipeType());
        }
    }

    public void addListeners() {
        recordButton.setOnAction(e -> {
            try {
                toggleRecord();
            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    }

    public void moveToNextScreen(String transcription, String mealType) {
        Scene scene = getScene();
        Window screen = scene.getWindow();
        if (screen instanceof Stage) {
            Stage current = (Stage) screen;
            TranscriptionScreen screenTwo = new TranscriptionScreen(transcription, mealType);
            current.setTitle("Ingredients");
            current.setScene(new Scene(screenTwo, 500, 500));
            current.setResizable(false);
            current.show();
        }
    }
}
