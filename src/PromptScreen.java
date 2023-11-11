public class PromptScreen extends Screen {
    private PromptButtons prompt;
    private String recipeType;

    PromptScreen() {
        super();
        setHeaderText("What would you like to make right now?");
        setFooterButtons("Back", "", "");
        prompt = new PromptButtons();
        this.setCenter(prompt);
        setLeftButtonAction("PantryPal", changePreviousScreenEvent);
    }

    @Override
    protected Screen createNextScreen() {
        return new RecordScreen(recipeType);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen();
    }
}
