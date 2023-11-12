public class HomeScreen extends Screen {
    private RecipeDisplay recipeDisplay;
    private RecipeList recipeList;
    HomeScreen(RecipeList rl) {
        super();
        this.recipeList = rl;
        setHeaderText("PantryPal");
        setCenterObject();
        setFooterButtons("", "New Recipe", "");
        setCenterButtonAction("Pick Meal", changeNextScreenEvent);
        recipeDisplay = new RecipeDisplay(this.recipeList);
        this.setCenter(recipeDisplay);
    }

    public RecipeDisplay getRecipeDisplay() {
        return this.recipeDisplay;
    }

    @Override
    protected Screen createNextScreen() {
        return new PromptScreen(this.recipeList);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen(this.recipeList);
    }
}
