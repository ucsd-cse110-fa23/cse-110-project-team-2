package client;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DetailedViewScreen extends Screen {
    private Recipe currentRecipe;
    private TextField currentRecipeTitle;
    private TextArea currentRecipeBody;
    private String currentUsername;

    DetailedViewScreen(String username, Recipe currentRecipe) {
        currentUsername = username;
        this.currentRecipe = currentRecipe;
        
        setHeaderText("Current Recipe: " + currentRecipe.getRecipeTitle());
        setFooterButtons("Back", "Delete", "Edit");
        setLeftButtonAction("PantryPal", this::changeScreenUpdateRecipe);
        setCenterButtonAction("PantryPal", arg0 -> {
            try {
                changeScreenDeleteRecipe(arg0);
            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        setRightButtonAction("Editing", this::changeNextScreenEvent);

        currentRecipeTitle = new TextField(currentRecipe.getRecipeTitle());
        currentRecipeBody = new TextArea(currentRecipe.getRecipe());
        currentRecipeBody.setMaxHeight(400);
        currentRecipeBody.setMaxWidth(400);
        currentRecipeBody.setEditable(false);
        currentRecipeBody.setWrapText(true);

        this.setCenter(currentRecipeBody);
    }

    @Override
    protected Screen createNextScreen() {
        return new EditScreen(currentUsername, currentRecipe);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen(currentUsername);
    }
    
    public void changeScreenUpdateRecipe(ActionEvent e) {
        currentRecipe.setRecipe(currentRecipeBody.getText());
        Screen previousScreen = createPreviousScreen();
        changeScreen(previousScreen);
    }
    
    public void changeScreenDeleteRecipe(ActionEvent e) throws IOException, InterruptedException {
        AppFrame.getRequest().performDeleteRecipe(currentUsername, currentRecipe.getRecipeTitle()+"@"+currentRecipe.getDate().toString());
        AppFrame.getAppRecipeList().deleteRecipe(currentRecipe);
        Screen previouScreen = createPreviousScreen();
        changeScreen(previouScreen);
    }
}
