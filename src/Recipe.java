import java.util.Date;
import javafx.scene.layout.HBox;

public class Recipe extends HBox{
    private String recipeTitle;
    private String recipe;
    private Date date;

    Recipe(String recipeTitle, String recipe, Date date){
        this.recipeTitle = recipeTitle;
        this.recipe = recipe;
        this.date = date;

        this.setPrefSize(500, 20); // sets size of Contact
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of Contact
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public String getRecipe() {
        return recipe;
    }

    public Date getDate() {
        return date;
    }

    //setter for recipe
    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
