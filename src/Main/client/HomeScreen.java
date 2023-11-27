package client;


public class HomeScreen extends Screen {
    private RecipeDisplay recipeDisplay;
    HomeScreen() {
        super();
        setHeaderText("PantryPal");
        setCenterObject();
        setFooterButtons("", "New Recipe", "");
        setCenterButtonAction("Pick Meal", this::changeNextScreenEvent);
        recipeDisplay = new RecipeDisplay();
        this.setCenter(recipeDisplay);
    }

    public RecipeDisplay getRecipeDisplay() {
        return this.recipeDisplay;
    }

    @Override
    protected Screen createNextScreen() {
        return new PromptScreen();
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen();
    }
}
