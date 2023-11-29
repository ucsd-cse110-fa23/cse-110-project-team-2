package client;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

public class TranscriptionScreen extends Screen{
    public static String ingredients;
    public static String mealType;
    private ChatGPT gpt;
    private String recipe;
    private String recipeTitle;
    private Image recipeImage;
    private Date date;
    private TextArea ingredArea;
    private DallE dallE;

    TranscriptionScreen(String transcription, String type){
        ingredients = transcription;
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
        return new RecipeScreen(recipe, recipeTitle, ingredients, mealType, recipeImage, date);
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
        gpt = new ChatGPT();
        dallE = new DallE();
        try {
            recipe = gpt.generate(ingredients, mealType);
            recipeTitle = gpt.generateTitle(ingredients, mealType);
            dallE.image(recipeTitle);
            recipeImage = new Image(new File("../../../recipeImage.png").toURI().toString());
            date = new Date();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        Screen nextScreen = createNextScreen();
        changeScreen(nextScreen);
    } 
}
