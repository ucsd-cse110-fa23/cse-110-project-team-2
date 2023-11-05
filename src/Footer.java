import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class Footer extends HBox {
    
    private Button addRecipeButton;
    private Button backButton;

    Footer() {
        addRecipeButton = new Button("New Recipe");
        backButton = new Button("Back");

        this.getChildren().addAll(addRecipeButton, backButton);
        this.setAlignment(Pos.CENTER);

    }

    public Button getAddRecipeButton() {
        return addRecipeButton;
    }
    public Button getBackButton() {
        return backButton;
    }
}
