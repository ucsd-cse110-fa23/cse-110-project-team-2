package client;


import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.json.JSONObject;

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
    private RequestSender request = new RequestSender();

    TranscriptionScreen(String transcription, String type){
        ingredients = transcription;
        mealType = type;
        setHeaderText("This is what we heard from you. Confirm your ingredients:");
        setFooterButtons("Cancel", "", "Next");
        setLeftButtonAction("PantryPal", this::changePreviousScreenEvent);
        setRightButtonAction("PantryPal", arg0 -> {
            try {
                changeScreenGenerateRecipeEvent(arg0);
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        ingredArea = new TextArea();
        ingredArea.setText(ingredients);
        this.setCenter(ingredArea);
    }

    @Override
    protected Screen createNextScreen() {
        return new RecipeScreen(recipe, recipeTitle, ingredients, mealType, date);
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

    public void changeScreenGenerateRecipeEvent (ActionEvent e) throws URISyntaxException, IOException, InterruptedException {
            //String data = AppFrame.getRequest().performGenerateRecipe("POST", ingredients, mealType);
            /*JSONObject dataJson = new JSONObject(data);
            recipe = dataJson.getString("recipe");
            recipeTitle = dataJson.getString("title");*/
            String recipeData = AppFrame.getRequest().performGenerateRecipe("POST", ingredients, mealType);
            String[] parsedData = recipeData.split("@");
            recipeTitle = parsedData[0];
            System.out.println(recipeData);
            recipe = parsedData[1];
            date = new Date();
        Screen nextScreen = createNextScreen();
        changeScreen(nextScreen);
    } 
}
