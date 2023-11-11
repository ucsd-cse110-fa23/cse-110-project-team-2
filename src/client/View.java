package client;

import javafx.stage.Screen;

public class View extends Screen{
    public View() {
        super();
        setHeaderText("PantryPal");
        setCenterObject();
        setFooterButtons("", "New Recipe", "");
        setCenterButtonAction("Pick Meal", changeNextScreenEvent);
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
