package client;

import java.util.*;

import client.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
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
        setLeftButtonAction("PantryPal", changeNextScreenEvent);
        setRightButtonAction("PantryPal", changeSavedRecipe);
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
    protected EventHandler<ActionEvent> changeSavedRecipe = new EventHandler<ActionEvent>() {
        // private Map<String, String> saved;
        public void handle(ActionEvent e) 
        {
            //Screen nextScreen = createNextScreen();
            Screen nextScreen = new HomeScreen(recipeList);
            String dummyRecipeTitle = "Recipe title";//make a second dummy and see if we can post and access to different entries
            String dummyRecipe = "Recipe details query doesn't like if their's spaces in Recipe Title";
            recipeObj = new Recipe(dummyRecipe, dummyRecipeTitle, date);
            String response = model.performRequest("POST", dummyRecipeTitle, dummyRecipe, null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Response");
            alert.setHeaderText(null);
            alert.setContentText(response);
            alert.showAndWait();
            //System.out.println(response);
            // Add recipe to recipelist
            //nextScreen.getChildren().add(recipeObj);
            recipeList.getChildren().add(recipeObj);
            //recipeList.getChildren().add(recipeObj);
            //recipeList.sortRecipes();
            changeScreen(nextScreen); 
        } 
    };
}