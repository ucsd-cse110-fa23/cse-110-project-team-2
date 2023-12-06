package main.java;



public class PromptScreen extends Screen {
    private PromptButtons prompt;
    private String recipeType;
    private String currentUsername;

    PromptScreen(String username) {
        super();
        this.currentUsername = username;
        setHeaderText("What would you like to make right now?");
        setFooterButtons("Back", "", "");
        prompt = new PromptButtons(this.getUsername());
        this.setCenter(prompt);
        setLeftButtonAction("PantryPal", this::changePreviousScreenEvent);
    }

    public String getUsername() {
        return currentUsername;
    }

    @Override
    protected Screen createNextScreen() {
        return new RecordScreen(recipeType, currentUsername);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen(currentUsername);
    }
}
