package client;


import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class EditScreen extends Screen {
    private Recipe currentRecipe;
    private TextArea editRecipeBox;
    private String currentUsername;

    EditScreen(String username, Recipe currentRecipe) {
        this.currentRecipe = currentRecipe;
        this.currentUsername = username;
        editRecipeBox = new TextArea(currentRecipe.getRecipe());
        editRecipeBox.setMaxHeight(400);
        editRecipeBox.setMaxWidth(400);
        editRecipeBox.setEditable(true);
        editRecipeBox.setWrapText(true);
        setHeaderText("Currently Editing: " + currentRecipe.getRecipeTitle());
        setFooterButtons("Back", "", "Save");
        setLeftButtonAction("Detailed View", this::changePreviousScreenEvent);
        setRightButtonAction("Detailed View", this::changeScreenSaveEdits);
        this.setCenter(editRecipeBox);
    }

    @Override
    protected Screen createNextScreen() {
        return new DetailedViewScreen(currentUsername, currentRecipe);
    }

    @Override
    protected Screen createPreviousScreen() {
        return new DetailedViewScreen(currentUsername, currentRecipe);
    }

    public Recipe getCurrentRecipe() {
        return currentRecipe;
    }

    public void changeScreenSaveEdits (ActionEvent e) {
        currentRecipe.setRecipe(editRecipeBox.getText());
        Screen nextScreen = createNextScreen();
        changeScreen(nextScreen);
    } 
}
