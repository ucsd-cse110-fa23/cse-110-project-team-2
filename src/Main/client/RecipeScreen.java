package client;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RecipeScreen extends Screen{
    private TextArea generatedRecipe;
    private String recipe;
    private String recipeTitle;
    private String ingreds;
    private String mealType;
    private ImageView recipeImage;
    private DallE dallE;
    private RequestSender request;
    private Recipe recipeObj;
    private Date date;
    private ChatGPT gpt;

    RecipeScreen(String recipe, String recipeTitle, String ingreds, String mealType, ImageView recipeImage, Date date){
        setHeaderText("Here is your recipe!");
        this.recipe = recipe;
        this.recipeTitle = recipeTitle;
        this.mealType = mealType;
        this.recipeImage = recipeImage;
        this.date = date;
        this.ingreds = ingreds;
        System.out.println("RECIPE IMAGE IS: " + recipeImage);
        recipeImage.setFitHeight(250);
        recipeImage.setFitWidth(250);
        recipeImage.setPreserveRatio(true);
        generatedRecipe = new TextArea(recipeTitle + "\n\n" + recipe);
        generatedRecipe.setMaxHeight(400);
        generatedRecipe.setMaxWidth(400);
        generatedRecipe.setEditable(false);
        generatedRecipe.setWrapText(true);
        setFooterButtons("Cancel", "Regenerate", "Save");
        setLeftButtonAction("PantryPal", this::changeNextScreenEvent);
        setCenterButtonAction("PantryPal", this::changeScreenGenerateRecipeEvent);
        setRightButtonAction("PantryPal", this::changeScreenSaveRecipe);
        this.setTop(recipeImage);
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
        return new RecipeScreen(recipe, recipeTitle, ingreds, mealType, recipeImage, date);
    }

    public void changeScreenSaveRecipe (ActionEvent e) {
        Screen nextScreen = new HomeScreen();
        recipeObj = new Recipe(recipeTitle, recipe, mealType, recipeImage, date);
        AppFrame.getAppRecipeList().getChildren().add(0, recipeObj);
        changeScreen(nextScreen);
    } 

    public void changeScreenGenerateRecipeEvent (ActionEvent e) {
        gpt = new ChatGPT();
        try {
            recipe = gpt.generate(ingreds, mealType);
            recipeTitle = gpt.generateTitle(ingreds, mealType);
            dallE.image(recipeTitle);
            String recipeFileName = recipeTitle.replaceAll("\\s+", "_").toLowerCase();
            Image currImage = new Image("file:"+recipeFileName+".png");
            recipeImage.setImage(currImage);
            date = new Date();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        Screen sameScreen = createSameScreen();
        changeScreen(sameScreen);
    } 
}
