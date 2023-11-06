import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RecipeFooter extends Footer {

    RecipeFooter() {
        this.leftButton.setText("Cancel");

        this.centerButton.setVisible(false);
        
        this.rightButton.setText("Save");
        addListeners();
    }

    public void addListeners(){
        leftButton.setOnAction(e->{
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

