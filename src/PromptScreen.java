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
    private Footer footer;
    private PromptButtons prompt;
    private Button backButton;

    PromptScreen() {
        footer = new Footer();
        prompt = new PromptButtons();
        this.setBottom(footer);
        this.setCenter(prompt);
        backButton = footer.getBackButton();
        addListeners();
    }
    public void addListeners() {
        // Add button functionality
        backButton.setOnAction(e -> {
            //Stage primaryStage2 = new Stage();
            //Window current = scene.getWindow();
            Scene scene = getScene();
            Window screen = scene.getWindow();
            if (screen instanceof Stage) {
                Stage current = (Stage) screen;
                HomeScreen screenTwo = new HomeScreen();
                current.setTitle("PantryPal");
                current.setScene(new Scene(screenTwo, 500, 500));
                current.setResizable(false);
                current.show();
            }
        });
    }
}

class PromptButtons extends HBox {
    private Button breakFastButton;
    private Button lunchButton;
    private Button dinnerButton;

    PromptButtons() {

        breakFastButton = new Button("BreakFast");
        lunchButton = new Button("Lunch");
        dinnerButton = new Button("Dinner");
        this.getChildren().addAll(breakFastButton, lunchButton, dinnerButton);
        this.setAlignment(Pos.CENTER);

    }

}
