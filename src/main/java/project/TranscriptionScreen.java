package project;
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
    public static String ingredients;
    public static String mealType;

    TranscriptionScreen(String transcription, String type){
        ingredients = transcription;
        mealType = type;
        header = new Header("Here is your ingredients: " + getTranscription());
        footer = new TranscriptionFooter();
        // nextButton = new Button("Next");
        // nextButton.setStyle("-fx-background-color: #43ED58");
        // cancelButton = new Button("Cancel");
        // cancelButton.setStyle("-fx-background-color: #43ED58");

        this.setTop(header);
        this.setBottom(footer);
    }

    public static String getTranscription(){
        return ingredients;
    }

    public static String getMealType() {
        return mealType;
    }
}

// class TempTranscriptionFooter extends Footer {
//     private Button cancelButton;
//     private Button nextButton;
//     private ChatGPT gpt;
//     private String recipe;

//     TempTranscriptionFooter() {
//         this.cancelButton.setText("Cancel");
//         this.centerButton.setVisible(false);
//         this.nextButton.setText("Next");
//         addListeners();
//     }

//     void addListeners() {
//         cancelButton.setOnAction(e->{
//             Scene scene = getScene();
//             Window screen = scene.getWindow();
//             if (screen instanceof Stage) {
//                 Stage current = (Stage) screen;
//                 HomeScreen screenTwo = new HomeScreen();
//                 current.setTitle("PantryPal");
//                 current.setScene(new Scene(screenTwo, 500, 500));
//                 current.setResizable(false);
//                 current.show();
//             }
//         });

//         nextButton.setOnAction(e -> {
//             Scene scene = getScene();
//             Window screen = scene.getWindow();
//             if (screen instanceof Stage) {
//                 Stage current = (Stage) screen;
//                 gpt = new ChatGPT();
//                 try {
//                     recipe = gpt.generate(TranscriptionScreen.getTranscription(), TranscriptionScreen.getMealType());
//                 } catch (IOException e1) {
//                     // TODO Auto-generated catch block
//                     e1.printStackTrace();
//                 } catch (InterruptedException e1) {
//                     // TODO Auto-generated catch block
//                     e1.printStackTrace();
//                 } catch (URISyntaxException e1) {
//                     // TODO Auto-generated catch block
//                     e1.printStackTrace();
//                 }
//                 RecipeScreen screenTwo = new RecipeScreen(recipe);
//                 current.setTitle("PantryPal");
//                 current.setScene(new Scene(screenTwo, 500, 500));
//                 current.setResizable(false);
//                 current.show();
//             }
//         });
//     }
// }
