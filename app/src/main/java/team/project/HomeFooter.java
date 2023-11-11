package team.project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;

public class HomeFooter extends Footer {
    
    HomeFooter() {
        this.leftButton.setVisible(false);

        this.centerButton.setText("New Recipe");
        addListeners();
        
        this.rightButton.setVisible(false);
    }

    public void addListeners() {
        centerButton.setOnAction(e -> {
            //Stage primaryStage2 = new Stage();
            //Window current = scene.getWindow();
            Scene scene = getScene();
            Window screen = scene.getWindow();
            if (screen instanceof Stage) {
                Stage current = (Stage) screen;
                PromptScreen screenTwo = new PromptScreen();
                current.setTitle("Pick Meal");
                current.setScene(new Scene(screenTwo, 500, 500));
                current.setResizable(false);
                current.show();
            }
        });
    }

}
