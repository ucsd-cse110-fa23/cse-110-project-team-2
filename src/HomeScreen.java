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

public class HomeScreen extends BorderPane {
    private Footer footer;
    private Button addRecipeButton;

    HomeScreen() {
        footer = new Footer();
        this.setBottom(footer);
        addRecipeButton = footer.getAddRecipeButton();
        addListeners();
    }
    public void addListeners() {
        // Add button functionality
        addRecipeButton.setOnAction(e -> {
            //Stage primaryStage2 = new Stage();
            //Window current = scene.getWindow();
            Scene scene = getScene();
            Window screen = scene.getWindow();
            if (screen instanceof Stage) {
                Stage current = (Stage) screen;
                PromptScreen screenTwo = new PromptScreen();
                current.setTitle("Pick Meal");
                current.setScene(new Scene(screenTwo, 500, 500));
                current.setResizable(false);
                current.show();
            }
        });
    }
}
