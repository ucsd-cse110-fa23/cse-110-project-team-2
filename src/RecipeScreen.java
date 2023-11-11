import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

public class RecipeScreen extends Screen{
    private TextArea generatedRecipe;

    RecipeScreen(String recipe){
        setHeaderText("Here is your recipe!");
        generatedRecipe = new TextArea(recipe);
        generatedRecipe.setMaxHeight(400);
        generatedRecipe.setMaxWidth(400);
        generatedRecipe.setEditable(false);
        generatedRecipe.setWrapText(true);
        setFooterButtons("Cancel", "", "Save");
        setLeftButtonAction("PantryPal", changeNextScreenEvent);
        setRightButtonAction("PantryPal", changePreviousScreenEvent);

        this.setCenter(generatedRecipe);
    }

    @Override
    protected Screen createNextScreen() {
        return new HomeScreen();
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen();
    }

    // action event 
    protected EventHandler<ActionEvent> changeScreenSaveRecipe = new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent e) 
        {
            Screen nextScreen = createNextScreen();
            changeScreen(nextScreen);
        } 
    };
}
