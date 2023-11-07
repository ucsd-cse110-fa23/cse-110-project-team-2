package project;

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


public class PromptScreen extends BorderPane {
    private Header header;
    private BackFooter footer;
    private PromptButtons prompt;

    PromptScreen() {
        header = new Header("What would you like to make right now?");
        footer = new BackFooter();
        prompt = new PromptButtons();
        this.setTop(header);
        this.setBottom(footer);
        this.setCenter(prompt);
    }
}

class PromptButtons extends HBox {
    private Button breakfastButton;
    private Button lunchButton;
    private Button dinnerButton;

    PromptButtons() {

        breakfastButton = new Button("BreakFast");
        lunchButton = new Button("Lunch");
        dinnerButton = new Button("Dinner");
        this.getChildren().addAll(breakfastButton, lunchButton, dinnerButton);
        this.setAlignment(Pos.CENTER);
        addListeners();
    }

    public void addListeners(){
        breakfastButton.setOnAction(e -> {
            String breakfast = "Breakfast";
            moveToNextScreen(breakfast);
        });

        lunchButton.setOnAction(e -> {
            String lunch = "Lunch";
            moveToNextScreen(lunch);
        });

        dinnerButton.setOnAction(e -> {
            String dinner = "Dinner";
            moveToNextScreen(dinner);
        });
    }

    public void moveToNextScreen(String type) {
        Scene scene = getScene();
        Window screen = scene.getWindow();
        if (screen instanceof Stage) {
            Stage current = (Stage) screen;
            RecordScreen screenTwo = new RecordScreen(type);
            current.setTitle("Recording");
            current.setScene(new Scene(screenTwo, 500, 500));
            current.setResizable(false);
            current.show();
        }
    }

}
