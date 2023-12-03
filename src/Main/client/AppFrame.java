package client;


import java.util.Date;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class AppFrame extends BorderPane {
    public static RecipeList recipeList;
    private HomeScreen startScreen;

    AppFrame() {
        AppFrame.recipeList = new RecipeList();

        // ImageView testerImage = new ImageView();
        // Image testImage = new Image("file:");
        // testerImage.setImage(testImage);
        // Date date1 = new Date();



        // Recipe brkfast1 = new Recipe("Apple Toast", "Toast the apples!", "Breakfast", testerImage, date1);
        // AppFrame.getAppRecipeList().getChildren().add(0, brkfast1);
        // Recipe brkfast2 = new Recipe("Blueberry Toast", "Toast the blueberries!", "Breakfast", testerImage, date1);
        // AppFrame.getAppRecipeList().getChildren().add(0, brkfast2);
        // Recipe lunch1 = new Recipe("Costco Hotdog", "Buy from Costco!", "Lunch", testerImage, date1);
        // AppFrame.getAppRecipeList().getChildren().add(0, lunch1);
        // Recipe dinner1 = new Recipe("Dino Nuggets", "Follow the cooking instructions!", "Dinner", testerImage, date1);
        // AppFrame.getAppRecipeList().getChildren().add(0, dinner1);


        startScreen = new HomeScreen();
        this.setCenter(startScreen);
    }
    
    public static RecipeList getAppRecipeList() {
        return recipeList;
    }
}
