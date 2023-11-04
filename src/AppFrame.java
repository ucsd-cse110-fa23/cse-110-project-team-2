import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class AppFrame extends BorderPane{
    private Footer footer;
    private Button addRecipeButton;

    AppFrame() {
        footer = new Footer();

        this.setBottom(footer);

        addRecipeButton = footer.getAddRecipeButton();

    }
}
