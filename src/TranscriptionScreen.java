import javafx.scene.layout.BorderPane;

public class TranscriptionScreen extends Screen{
    public static String ingredients;
    public static String mealType;

    TranscriptionScreen(String transcription, String type){
        ingredients = transcription;
        mealType = type;
        setHeaderText("This is what we heard from you. Confirm your ingredients: " + getTranscription());
        setFooterButtons("Cancel", "", "Next");
        setLeftButtonAction("PantryPal", changePreviousScreenEvent);
        setRightButtonAction("PantryPal", changeNextScreenEvent);
    }

    @Override
    protected Screen createNextScreen() {
        return new RecipeScreen("filler");
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
}
