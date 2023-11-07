

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class Footer extends HBox {
    
    public Button leftButton;
    public Button centerButton;
    public Button rightButton;

    Footer() {
        leftButton = new Button();
        centerButton = new Button();
        rightButton = new Button();

        this.getChildren().addAll(leftButton, centerButton, rightButton);
        this.setAlignment(Pos.CENTER);

    }

}
