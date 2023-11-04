import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class Footer extends HBox {
    
    private Button addRecipeButton;

    Footer() {
        addRecipeButton = new Button("New Recipe");
        this.getChildren().addAll(addRecipeButton);
        this.setAlignment(Pos.CENTER);
    }

    public Button getAddRecipeButton() {
        return addRecipeButton;
    }
}
