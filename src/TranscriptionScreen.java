import java.io.IOException;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

public class TranscriptionScreen extends Screen{
    public static String ingredients;
    public static String mealType;
    private ChatGPT gpt;
    private String recipe;

    TranscriptionScreen(String transcription, String type){
        ingredients = transcription;
        mealType = type;
        setHeaderText("This is what we heard from you. Confirm your ingredients: " + getTranscription());
        setFooterButtons("Cancel", "", "Next");
        setLeftButtonAction("PantryPal", changePreviousScreenEvent);
        setRightButtonAction("PantryPal", changeScreenGenerateRecipeEvent);
    }

    @Override
    protected Screen createNextScreen() {
        return new RecipeScreen(recipe);
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

    protected EventHandler<ActionEvent> changeScreenGenerateRecipeEvent = new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent e) 
        {
            gpt = new ChatGPT();
            try {
                recipe = gpt.generate(TranscriptionScreen.getTranscription(), TranscriptionScreen.getMealType());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Screen nextScreen = createNextScreen();
            changeScreen(nextScreen);
        } 
    };
}
