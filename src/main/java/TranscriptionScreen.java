

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TranscriptionScreen extends Screen {
    public static String ingredients;
    public static String mealType;
    private ChatGPT gpt;
    private String recipe;
    private String recipeTitle;
    private ImageView recipeImage;
    private Date date;
    private TextArea ingredArea;
    private RequestSender request = new RequestSender();
    private String currentUsername;
    private DallE dallE;

    TranscriptionScreen(String username, String transcription, String type) {
        currentUsername = username;
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
        return new RecipeScreen(currentUsername, recipe, recipeTitle, ingredients, mealType, date, recipeImage);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen(currentUsername);
    }

    public static String getTranscription() {
        return ingredients;
    }

    public static String getMealType() {
        return mealType;
    }

    public void changeScreenGenerateRecipeEvent(ActionEvent e) {
        try {
            String data = AppFrame.getRequest().performGenerateRecipe(ingredients, mealType);
            JSONObject dataJson = new JSONObject(data);
            recipe = dataJson.getString("recipe");
            recipeTitle = dataJson.getString("title");
            date = new Date(); 
            //System.out.println(recipeTitle);
            //String recipeFileName = recipeTitle.replaceAll("\\s+", "_").toLowerCase();
            //Image currImage = new Image("file:"+recipeFileName+".png");
            //System.out.println("currImage points to: " + currImage);
            recipeImage = new ImageView();
            Image img = AppFrame.getRequest().performGenerateImage(recipeTitle);
            recipeImage.setImage(img);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        Screen nextScreen = createNextScreen();
        changeScreen(nextScreen);
    }
}
