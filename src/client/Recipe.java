package client;

import java.util.Date;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Recipe extends HBox{
    private String recipeTitle;
    private String recipe;
    private Date date;
    //private TextField taskName;
    private Button recipeButton;

    Recipe(String recipeTitle, String recipe, Date date){
        this.recipeTitle = recipeTitle;
        this.recipe = recipe;
        this.date = date;

        this.setPrefSize(500, 20); // sets size of Contact
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of Contact

        System.out.println("RECIPE TITLE IS: " + recipeTitle);
        recipeButton = new Button(recipeTitle); // creates a button for marking the task as done
        recipeButton.setPrefSize(500, 20);
        recipeButton.setPrefHeight(Double.MAX_VALUE);
        recipeButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button
        recipeButton.setOnAction(this::changeToDetailedView);
        this.getChildren().add(recipeButton);
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public String getRecipe() {
        return recipe;
    }

    public Date getDate() {
        return date;
    }

    public Button getRecipeButton() {
        return recipeButton;
    }

    //setter for recipe
    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void changeToDetailedView (ActionEvent e) {
        Scene scene = getScene();
        Window screen = scene.getWindow();
        if (screen instanceof Stage) {
            Stage current = (Stage) screen;
            // Recipe recipeObj = new Recipe(recipeTitle, recipe, date);
            Screen nextScreen = new DetailedViewScreen(this);
            current.setTitle("Detailed View");
            current.setScene(new Scene(nextScreen, 500, 500));
            current.setResizable(false);
            current.show();
        }
    } 
}
