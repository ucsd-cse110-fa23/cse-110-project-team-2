package main.java;



public class HomeScreen extends Screen {
    private RecipeDisplay recipeDisplay;
    private String currentUsername;
    HomeScreen(String username) {
        super();
        currentUsername = username;
        setHeaderText("PantryPal");
        setCenterObject();
        setFooterButtons("", "New Recipe", "");
        setCenterButtonAction("Pick Meal", this::changeNextScreenEvent);
        recipeDisplay = new RecipeDisplay();
        this.setCenter(recipeDisplay);
    }

    // This should be a server request
    public RecipeDisplay getRecipeDisplay() {
        return this.recipeDisplay;
    }

    @Override
    protected Screen createNextScreen() {
        return new PromptScreen(currentUsername);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen(currentUsername);
    }
}
