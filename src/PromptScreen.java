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

    }

}
