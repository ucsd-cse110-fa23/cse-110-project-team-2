package client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class TranscriptionScreen extends Screen{
    public static String ingredients;
    public static String mealType;
    private ChatGPT gpt;
    private String recipe;
    private String recipeTitle;
    private Date date;
    private TextArea ingredArea; 
    private Controller controller;

    TranscriptionScreen(String transcription, String type){
        controller = new Controller();

        //right here lets do a getTranscription and assign it to ingredients
        ingredients = controller.performRequestTranscription("GET");
        mealType = type;
        setHeaderText("This is what we heard from you. Confirm your ingredients:");
        setFooterButtons("Cancel", "", "Next");
        setLeftButtonAction("PantryPal", this::changePreviousScreenEvent);
        setRightButtonAction("PantryPal", this::changeScreenGenerateRecipeEvent);
        ingredArea = new TextArea();
        ingredArea.setText(ingredients);
        this.setCenter(ingredArea);
    }

    @Override
    protected Screen createNextScreen() {
        return new RecipeScreen(recipe, recipeTitle, date);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen();
    }

    public static String getTranscription(){
        return ingredients;
    }

    public static String getMealType() {
        return mealType;
    }

    public void changeScreenGenerateRecipeEvent (ActionEvent e) {
        String responseOfRecipePost = controller.performRequestGPT("POST");
        String recipeData = controller.performRequestGPT("GET");

        System.out.println(recipeData);

        String[] parsedData = recipeData.split("@");
        recipeTitle = parsedData[0];
        recipe = parsedData[1];

        date = new Date();

        Screen nextScreen = createNextScreen();
        changeScreen(nextScreen);
    } 
}
