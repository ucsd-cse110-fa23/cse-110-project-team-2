package client;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class RecipeDisplay extends BorderPane{
    private RecipeList recipeList;

    RecipeDisplay(RecipeList rl) {
        this.recipeList = rl;
        // this.setCenter(recipeList);
        ScrollPane scroller = new ScrollPane(recipeList);
        scroller.setFitToWidth(true);
        scroller.setFitToHeight(true);
        this.setCenter(scroller);
    }


    public RecipeList getrecipeList() {
        return this.recipeList;
    }
}
