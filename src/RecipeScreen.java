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
    private Recipe recipeObj;
    //private RecipeList recipeList;
    private Date date;

    RecipeScreen(String recipe, String recipeTitle, Date date){
        setHeaderText("Here is your recipe!");
        //recipeList = new RecipeList();
        this.recipe = recipe;
        this.recipeTitle = recipeTitle;
        this.date = date;
        generatedRecipe = new TextArea(recipeTitle + "\n\n" + recipe);
        generatedRecipe.setMaxHeight(400);
        generatedRecipe.setMaxWidth(400);
        generatedRecipe.setEditable(false);
        generatedRecipe.setWrapText(true);
        setFooterButtons("Cancel", "", "Save");
        setLeftButtonAction("PantryPal", changeNextScreenEvent);
        setRightButtonAction("PantryPal", changeSavedRecipe);
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
    protected EventHandler<ActionEvent> changeSavedRecipe = new EventHandler<ActionEvent>() {
        // private Map<String, String> saved;
        public void handle(ActionEvent e) 
        {
            //Screen nextScreen = createNextScreen();
            Screen nextScreen = new HomeScreen();
            recipeObj = new Recipe(recipe, recipeTitle, date);
            // Add recipe to recipelist
            //nextScreen.getChildren().add(recipeObj);
            ((HomeScreen)(nextScreen)).getRecipeDisplay().getRecipeList().getChildren().add(recipeObj);
            //recipeList.getChildren().add(recipeObj);
            //recipeList.sortRecipes();
            changeScreen(nextScreen);
        } 
    };
}
