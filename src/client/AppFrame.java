package client;

import javafx.scene.layout.BorderPane;

public class AppFrame extends BorderPane {
    public static RecipeList recipeList;
    private HomeScreen startScreen;

    AppFrame() {
        AppFrame.recipeList = new RecipeList();
        startScreen = new HomeScreen(recipeList);
        this.setCenter(startScreen);
    }
    
    public static RecipeList getAppRecipeList() {
        return recipeList;
    }
}
