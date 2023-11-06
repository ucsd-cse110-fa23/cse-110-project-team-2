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

public class TranscriptionScreen extends BorderPane{
    private Header header;
    private TranscriptionFooter footer;
    private Whisper Whisper;

    TranscriptionScreen(String transcription, String mealType){
        String ingredients = transcription;
        header = new Header("Here is your ingredients: " + getTranscription());
        footer = new TranscriptionFooter();
        // nextButton = new Button("Next");
        // nextButton.setStyle("-fx-background-color: #43ED58");
        // cancelButton = new Button("Cancel");
        // cancelButton.setStyle("-fx-background-color: #43ED58");

        this.setTop(header);
        this.setBottom(footer);
    }

    public String getTranscription(){
        return ingredients;
    }
}
