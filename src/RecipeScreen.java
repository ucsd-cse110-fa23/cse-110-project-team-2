import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class RecipeScreen extends Screen{
    private Header header;
    private TextArea generatedRecipe;
    private RecipeFooter footer;

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
}
