package main.java;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class RecipeScreen extends BorderPane{
    private Header header;
    private TextArea generatedRecipe;
    private RecipeFooter footer;

    RecipeScreen(String recipe){
        header = new Header("Here is your recipe!");
        generatedRecipe = new TextArea(recipe);
        generatedRecipe.setMaxHeight(400);
        generatedRecipe.setMaxWidth(400);
        generatedRecipe.setEditable(false);
        generatedRecipe.setWrapText(true);
        footer = new RecipeFooter();
        // nextButton = new Button("Next");
        // nextButton.setStyle("-fx-background-color: #43ED58");
        // cancelButton = new Button("Cancel");
        // cancelButton.setStyle("-fx-background-color: #43ED58");

        this.setTop(header);
        this.setCenter(generatedRecipe);
        this.setBottom(footer);
    }
}
