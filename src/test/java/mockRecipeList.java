import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

public class mockRecipeList extends RecipeList{
    
    private String username = "test";
    private JSONObject rListJson;
    private 

    mockRecipeList() throws JSONException, ParseException {
        loadRecipes(null, username);
    }
}


