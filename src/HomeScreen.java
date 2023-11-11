public class HomeScreen extends Screen {

    HomeScreen() {

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
