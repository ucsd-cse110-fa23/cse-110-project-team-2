import java.text.ParseException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class mockRecipeList extends RecipeList{
    
    private String username = "test";
    private JSONObject rListJson;
    private JSONObject breakfastRecipe;
    private JSONObject lunchRecipe;
    private JSONObject dinnerRecipe;

    mockRecipeList() throws JSONException, ParseException {

        rListJson = new JSONObject();

        breakfastRecipe = new JSONObject();
        breakfastRecipe.put("title", "Apple Toast");
        Date date1 = new Date();
        breakfastRecipe.put("date", date1.toString());
        breakfastRecipe.put("mealType", "Breakfast");
        breakfastRecipe.put("recipe", "Toast some apples.");
        rListJson.put("AppleToast"+date1, breakfastRecipe);

        lunchRecipe = new JSONObject();
        lunchRecipe.put("title", "Bacon Sandwich");
        Date date2 = new Date();
        breakfastRecipe.put("date", date2.toString());
        breakfastRecipe.put("mealType", "Lunch");
        breakfastRecipe.put("recipe", "Sandwich the bacon.");
        rListJson.put("BaconToast"+date2, lunchRecipe);

        dinnerRecipe = new JSONObject();
        dinnerRecipe.put("title", "Chicken Pot Pie");
        Date date3 = new Date();
        breakfastRecipe.put("date", date3.toString());
        breakfastRecipe.put("mealType", "Dinner");
        breakfastRecipe.put("recipe", "Pot pie the chicken.");
        rListJson.put("ChickenPotPie"+date3, dinnerRecipe);

        loadRecipes(rListJson, username);
    }
}

