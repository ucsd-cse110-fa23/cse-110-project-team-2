package client;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DetailedViewScreen extends Screen {
    private Recipe currentRecipe;
    private TextField currentRecipeTitle;
    private TextArea currentRecipeBody;

    DetailedViewScreen(Recipe currentRecipe) {
        this.currentRecipe = currentRecipe;
        
        setHeaderText("Current Recipe: " + currentRecipe.getRecipeTitle());
        setFooterButtons("Back", "Delete", "Edit");
        setLeftButtonAction("PantryPal", this::changeScreenUpdateRecipe);
        setCenterButtonAction("PantryPal", this::changeScreenDeleteRecipe);
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
        return new EditScreen(currentRecipe);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen();
    }
    
    public void changeScreenUpdateRecipe(ActionEvent e) {
        currentRecipe.setRecipe(currentRecipeBody.getText());
        Screen previousScreen = createPreviousScreen();
        changeScreen(previousScreen);
    }
    
    public void changeScreenDeleteRecipe(ActionEvent e) {
        AppFrame.getAppRecipeList().deleteRecipe(currentRecipe);
        Screen previouScreen = createPreviousScreen();
        changeScreen(previouScreen);
    }
}
