package client;


import java.util.Date;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class AppFrame extends BorderPane {
    public static RecipeList recipeList;
    private HomeScreen startScreen;

    AppFrame() {
        AppFrame.recipeList = new RecipeList();

        startScreen = new HomeScreen();
        this.setCenter(startScreen);
    }
    
    public static RecipeList getAppRecipeList() {
        return recipeList;
    }
}
