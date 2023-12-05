


import javafx.scene.layout.BorderPane;

public class AppFrame extends BorderPane {
    public static RecipeList recipeList;
    private static RequestSender request;
    private LoginScreen startScreen;

    AppFrame() {
        AppFrame.recipeList = new RecipeList();
        AppFrame.request = new RequestSender();
        startScreen = new LoginScreen();
        this.setCenter(startScreen);
    }
    
    public static RecipeList getAppRecipeList() {
        return recipeList;
    }

    public static RequestSender getRequest() {
        return request;
    }
}
