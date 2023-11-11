import java.util.*;

import client.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

public class RecipeScreen extends Screen{
    private TextArea generatedRecipe;
    private Map<String, String> saved;
    private String recipe;
    private String recipeTitle;
    private Model model;

    RecipeScreen(String recipe, String recipeTitle){
        saved = new HashMap<>();
        setHeaderText("Here is your recipe!");
        this.recipe = recipe;
        this.recipeTitle = recipeTitle;
        generatedRecipe = new TextArea(recipeTitle + "\n\n" + recipe);
        generatedRecipe.setMaxHeight(400);
        generatedRecipe.setMaxWidth(400);
        generatedRecipe.setEditable(false);
        generatedRecipe.setWrapText(true);
        setFooterButtons("Cancel", "", "Save");
        setLeftButtonAction("PantryPal", changeNextScreenEvent);
        setRightButtonAction("PantryPal", changeNextScreenEvent);
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
    protected EventHandler<ActionEvent> changeNextScreenEvent = new EventHandler<ActionEvent>() {
        // private Map<String, String> saved;
        public void handle(ActionEvent e) 
        {
            Screen nextScreen = createPreviousScreen();
            saved.put(recipeTitle, recipe);
            System.out.println(saved);
            changeScreen(nextScreen);
        } 
    };
}
