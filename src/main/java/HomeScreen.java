package main.java;

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
    private Header header;
    private HomeFooter footer;

    HomeScreen() {
        header = new Header("PantryPal");
        footer = new HomeFooter();
        this.setTop(header);
        this.setBottom(footer);
    }
}
