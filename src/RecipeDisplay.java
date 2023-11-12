import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class RecipeDisplay extends BorderPane{
    private RecipeList RecipeList;

    RecipeDisplay() {
        RecipeList = new RecipeList();
        this.setCenter(RecipeList);
        ScrollPane scroller = new ScrollPane(RecipeList);
        scroller.setFitToWidth(true);
        scroller.setFitToHeight(true);
    }
}
