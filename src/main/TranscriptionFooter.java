import java.io.IOException;
import java.net.URISyntaxException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TranscriptionFooter extends Footer {
    private ChatGPT gpt;
    private String recipe;

    TranscriptionFooter() {
        this.leftButton.setText("Cancel");

        this.centerButton.setVisible(false);
        
        this.rightButton.setText("Next");
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
        rightButton.setOnAction(e->{
            Scene scene = getScene();
            Window screen = scene.getWindow();
            if (screen instanceof Stage) {
                Stage current = (Stage) screen;
                gpt = new ChatGPT();
                try {
                    recipe = gpt.generate(TranscriptionScreen.getTranscription(), TranscriptionScreen.getMealType());
                    // recipe = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                RecipeScreen screenTwo = new RecipeScreen(recipe);
                current.setTitle("PantryPal");
                current.setScene(new Scene(screenTwo, 500, 500));
                current.setResizable(false);
                current.show();
            }
        });
    }
    
}
