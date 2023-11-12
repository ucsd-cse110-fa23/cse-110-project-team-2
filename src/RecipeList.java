import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.layout.VBox;

public class RecipeList extends VBox{
    RecipeList() {
        this.setSpacing(5); // sets spacing between Recipes
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }

    public void sortRecipes() {
        //Want to sort all recipes in RecipeList by date
        // hint 1: this.getChildren() gets the list of Recipes
        // hint 2: Collections.sort() can be used to sort the Recipes
        // hint 3: Recipe.getRecipeName().setText() sets the text of the Recipe
        ArrayList<Recipe> RecipeList = new ArrayList<Recipe>();
        for(int i = 0; i < this.getChildren().size(); i++){
            RecipeList.add(((Recipe) this.getChildren().get(i)));
        }
        for(int i = 0; i < RecipeList.size(); i++){
            Recipe curr = RecipeList.get(i);
            for(int j = 1; j < RecipeList.size(); j++){
                Recipe next = RecipeList.get(j);
                if(curr.getDate().compareTo(next.getDate()) < 0){
                    Collections.swap(RecipeList, i, j);
                }
            }
        }
    }
}
