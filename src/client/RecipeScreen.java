package client;

import java.util.*;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class RecipeScreen extends Screen{
    private TextArea generatedRecipe;
    private Map<String, String> saved;
    private String recipe;
    private String recipeTitle;
    private Model model;
    private Recipe recipeObj;
    private Date date;
    private RecipeList recipeList;

    RecipeScreen(String recipe, String recipeTitle, Date date, RecipeList rl){
        setHeaderText("Here is your recipe!");
        this.recipeList = rl;
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
        return new HomeScreen(this.recipeList);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen(this.recipeList);
    }

    // action event 

    public void changeScreenSaveRecipe (ActionEvent e) {
        Screen nextScreen = new HomeScreen(recipeList);
        recipeObj = new Recipe(recipeTitle, recipe, date);
        String response = model.performRequest("POST", recipeTitle, recipe, null);
        System.out.println(response);
        recipeList.getChildren().add(recipeObj);
        changeScreen(nextScreen);
    } 
}
