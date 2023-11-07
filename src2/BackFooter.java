

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class BackFooter extends Footer {

        BackFooter() {
        
        this.leftButton.setText("Back");

        this.centerButton.setVisible(false);

        this.rightButton.setVisible(false);
        addListeners();
    }

    public void addListeners() {
        leftButton.setOnAction(e -> {
            //Stage primaryStage2 = new Stage();
            //Window current = scene.getWindow();
            Scene scene = getScene();
            Window screen = scene.getWindow();
            if (screen instanceof Stage) {
                Stage current = (Stage) screen;
                HomeScreen screenTwo = new HomeScreen();
                current.setTitle("PantryPal");
                current.setScene(new Scene(screenTwo, 500, 500));
                current.setResizable(false);
                current.show();
            }
        });
    }
}
