package client;

import java.util.Date;
import javafx.event.ActionEvent;

public class TranscriptionScreen extends Screen{
    public static String ingredients;
    public static String mealType;
    private ChatGPT gpt;
    private String recipe;
    private String recipeTitle;
    private Date date;

    TranscriptionScreen(String transcription, String type){
        ingredients = transcription;
        mealType = type;
        setHeaderText("This is what we heard from you. Confirm your ingredients: " + getTranscription());
        setFooterButtons("Cancel", "", "Next");
        setLeftButtonAction("PantryPal", this::changePreviousScreenEvent);
        setRightButtonAction("PantryPal", this::changeScreenGenerateRecipeEvent);
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
        // possibly call a GET for the chatgpt here
        gpt = new ChatGPT();
        // try {
            recipe = "Ingredients: \r\n" + //
                    "-2 boneless, skinless chicken breasts, cut into cubes\r\n" + //
                    "-2 large eggs\r\n" + //
                    "-2 tablespoons of fresh herbs (such as thyme, oregano, rosemary, or sage)\r\n" + //
                    "-2 garlic cloves, minced\r\n" + //
                    "-¼ teaspoon of black pepper\r\n" + //
                    "-2 tablespoons of olive oil\r\n" + //
                    "-3 tablespoons of breadcrumbs\r\n" + //
                    "\r\n" + //
                    "Instructions:\r\n" + //
                    "\r\n" + //
                    "1. Preheat the oven to 350 degrees Fahrenheit.\r\n" + //
                    "\r\n" + //
                    "2. In a medium bowl, mix together the fresh herbs, garlic, black pepper, breadcrumbs, and olive oil.\r\n" + //
                    "\r\n" + //
                    "3. Place the cubed chicken in a baking dish and top with the herb mixture.\r\n" + //
                    "\r\n" + //
                    "4. Crack the eggs over the top of the chicken and spread them out evenly.\r\n" + //
                    "\r\n" + //
                    "5. Bake for 25 minutes until the chicken is cooked through and the eggs have set.\r\n" + //
                    "\r\n" + //
                    "6. Serve hot and enjoy!";
            recipeTitle = "Herb-Crusted Chicken and Egg Bake";
            date = new Date();
        // } catch (IOException e1) {
        //     e1.printStackTrace();
        // } catch (InterruptedException e1) {
        //     e1.printStackTrace();
        // } catch (URISyntaxException e1) {
        //     e1.printStackTrace();
        // }
        Screen nextScreen = createNextScreen();
        changeScreen(nextScreen);
    } 
}
