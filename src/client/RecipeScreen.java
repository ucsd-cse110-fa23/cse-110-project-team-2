package client;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class RecipeScreen extends Screen{
    private TextArea generatedRecipe;
    private String recipe;
    private String recipeTitle;
    private String ingreds;
    private String mealType;
    private RequestSender request = new RequestSender();
    private Recipe recipeObj;
    private Date date;
    private ChatGPT gpt;

    RecipeScreen(String recipe, String recipeTitle, String ingreds, String mealType, Date date){
        setHeaderText("Here is your recipe!");
        this.recipe = recipe;
        this.recipeTitle = recipeTitle;
        this.date = date;
        this.ingreds = ingreds;
        generatedRecipe = new TextArea(recipeTitle + "\n\n" + recipe);
        generatedRecipe.setMaxHeight(400);
        generatedRecipe.setMaxWidth(400);
        generatedRecipe.setEditable(false);
        generatedRecipe.setWrapText(true);
        setFooterButtons("Cancel", "Regenerate", "Save");
        setLeftButtonAction("PantryPal", this::changeNextScreenEvent);
        setCenterButtonAction("PantryPal", this::changeScreenGenerateRecipeEvent);
        setRightButtonAction("PantryPal", this::changeScreenSaveRecipe);
        this.setCenter(generatedRecipe);
        this.request = new RequestSender();
    }

    @Override
    protected Screen createNextScreen() {
        return new HomeScreen();
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen();
    }

    protected Screen createSameScreen() {
        return new RecipeScreen(recipe, recipeTitle, ingreds, mealType, date);
    }

    public void changeScreenSaveRecipe (ActionEvent e) {
        Screen nextScreen = new HomeScreen();
        recipeObj = new Recipe(recipeTitle, recipe, date);
        AppFrame.getAppRecipeList().getChildren().add(0, recipeObj);
        changeScreen(nextScreen);
    } 

    public void changeScreenGenerateRecipeEvent (ActionEvent e) {
        try {
            String data = AppFrame.getRequest().performGenerateRecipe(ingreds, mealType);
            JSONObject dataJson = new JSONObject(data);
            recipe = dataJson.getString("recipe");
            recipeTitle = dataJson.getString("title");
            date = new Date();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        Screen sameScreen = createSameScreen();
        changeScreen(sameScreen);
    } 
}
