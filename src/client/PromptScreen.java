package client;

public class PromptScreen extends Screen {
    private PromptButtons prompt;
    private String recipeType;
    private RecipeList recipeList;

    PromptScreen(RecipeList rl) {
        super();
        this.recipeList = rl;
        setHeaderText("What would you like to make right now?");
        setFooterButtons("Back", "", "");
        prompt = new PromptButtons(this.recipeList);
        this.setCenter(prompt);
        setLeftButtonAction("PantryPal", this::changePreviousScreenEvent);
    }

    @Override
    protected Screen createNextScreen() {
        return new RecordScreen(recipeType, this.recipeList);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen(this.recipeList);
    }
}
