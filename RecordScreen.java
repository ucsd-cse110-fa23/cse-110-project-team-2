import java.io.File;

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

    RecordScreen(String type) {
        recipeType = type;
        header = new Header("What ingredients do you have now? You said you wanted: " + getRecipeType());
        footer = new BackFooter();
        recordButton = new Button("Record");
        recordButton.setStyle("-fx-background-color: #43ED58");
        recorder = new Recorder();
        this.setTop(header);
        this.setCenter(recordButton);
        this.setBottom(footer);
        addListeners();
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void toggleRecord() {
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
        }
    }

    public void addListeners() {
        recordButton.setOnAction(e -> {
            toggleRecord();
        });
    }
}
