package client;


import javafx.scene.layout.BorderPane;

public class AppFrame extends BorderPane {
    public static RecipeList recipeList;
    private HomeScreen startScreen;
    private static RequestSender request;

    AppFrame() {
        AppFrame.recipeList = new RecipeList();
        AppFrame.request = new RequestSender();
        startScreen = new HomeScreen();
        this.setCenter(startScreen);
    }
    
    public static RecipeList getAppRecipeList() {
        return recipeList;
    }

    public static RequestSender getRequest() {
        return request;
    }
}
