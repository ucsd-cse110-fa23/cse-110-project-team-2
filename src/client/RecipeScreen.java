package client;

import java.util.*;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class RecipeScreen extends Screen{
    private TextArea generatedRecipe;
    private String recipe;
    private String recipeTitle;
    private Model model;
    private Recipe recipeObj;
    private Date date;

    RecipeScreen(String recipe, String recipeTitle, Date date){
        setHeaderText("Here is your recipe!");
        this.recipe = recipe;
        this.recipeTitle = recipeTitle;
        this.date = date;
        generatedRecipe = new TextArea(recipeTitle + "\n\n" + recipe);
        generatedRecipe.setMaxHeight(400);
        generatedRecipe.setMaxWidth(400);
        generatedRecipe.setEditable(false);
        generatedRecipe.setWrapText(true);
        setFooterButtons("Cancel", "", "Save");
        setLeftButtonAction("PantryPal", this::changeNextScreenEvent);
        setRightButtonAction("PantryPal", this::changeScreenSaveRecipe);
        this.setCenter(generatedRecipe);
        this.model = new Model();
    }

    @Override
    protected Screen createNextScreen() {
        return new HomeScreen();
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen();
    }

    public void changeScreenSaveRecipe (ActionEvent e) {
        Screen nextScreen = new HomeScreen();
        recipeObj = new Recipe(recipeTitle, recipe, date);
        //String response = model.performRequest("POST", recipeTitle, recipe, null);
        //System.out.println(response);
        AppFrame.getAppRecipeList().getChildren().add(recipeObj);
        changeScreen(nextScreen);
    } 
}
