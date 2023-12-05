

import java.awt.datatransfer.Clipboard;

import com.sun.javafx.tk.Toolkit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Clipboard;

public class DetailedViewScreen extends Screen {
    private Recipe currentRecipe;
    private TextField currentRecipeTitle;
    private TextArea currentRecipeBody;
    private ImageView currentRecipeImage;
    private Button shareButton;
    private URLHandler urlHandler;

    DetailedViewScreen(Recipe currentRecipe) {
        this.currentRecipe = currentRecipe;
        this.currentRecipeImage = currentRecipe.getRecipeImage();
        
        setHeaderText("Current Recipe: " + currentRecipe.getRecipeTitle());
        setFooterButtons("Back", "Delete", "Edit");
        setLeftButtonAction("PantryPal", this::changeScreenUpdateRecipe);
        setCenterButtonAction("PantryPal", this::changeScreenDeleteRecipe);
        setRightButtonAction("Editing", this::changeNextScreenEvent);

        System.out.println("CURRENT RECIPE IMAGE IS: " + currentRecipe.getRecipeImage());

        // ImageView testImageView = new ImageView();
        // Image testImage = new Image("file:recipeImage.png");

        // testImageView.setImage(testImage);

        currentRecipeTitle = new TextField(currentRecipe.getRecipeTitle());
        currentRecipeBody = new TextArea(currentRecipe.getRecipe());
        currentRecipeBody.setMaxHeight(400);
        currentRecipeBody.setMaxWidth(400);
        currentRecipeBody.setEditable(false);
        currentRecipeBody.setWrapText(true);

        // this.setTop(testImageView);
        this.setTop(currentRecipeImage);
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

    public void shareButtonAction(EventHandler<ActionEvent> eventHandler) {
        shareButton.setOnAction(eventHandler);
    }

    public void copyURL(){
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        clip.setContents();//inside: (link, link)
    }
}
