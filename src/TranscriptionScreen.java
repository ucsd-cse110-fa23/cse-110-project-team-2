import java.io.IOException;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TranscriptionScreen extends Screen{
    public static String ingredients;
    public static String mealType;
    private ChatGPT gpt;
    private String recipe;
    private String recipeTitle;

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
        return new RecipeScreen(recipe, recipeTitle);
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
                recipe = "Ingredients: \n - 2 boneless chicken breasts, cut into 1" cubes - 2 eggs
                - 2 cloves garlic, minced
                - 1 onion, thinly sliced
                - 2 tablespoons olive oil
                - Salt and pepper, to taste
                - 1/4 teaspoon dried oregano
                - 1/4 teaspoon dried thyme
                
                Instructions:
                
                1. Preheat oven to 350 degrees F.
                
                2. In a pan over medium heat, heat olive oil and add chicken cubes. Cook for 3-5 minutes or until chicken is lightly browned and cooked through.
                
                3. Transfer chicken to a baking dish.
                
                4. In the same pan, sauté garlic and onion for 3-5 minutes or until soft.
                
                5. Add garlic and onion to the baking dish.
                
                6. In a small bowl, beat the eggs and pour over chicken and vegetables.
                
                7. Sprinkle oregano, thyme, salt, and pepper over the top.
                
                8. Bake in preheated oven for 25-30 minutes or until the eggs are cooked through.
                
                9. Enjoy!"
                recipeTitle = "Chicken Egg 'Surprise'";
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
    };
}
