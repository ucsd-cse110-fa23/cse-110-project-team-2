package client;

import java.util.Date;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Recipe extends HBox{
    private String recipeTitle;
    private String recipe;
    private Date date;
    //private TextField taskName;
    private Button recipeButton;
    private Model model;

    Recipe(String recipeTitle, String recipe, Date date){
        this.recipeTitle = recipeTitle;
        this.recipe = recipe;
        this.date = date;
        this.model = new Model();

        this.setPrefSize(500, 20); // sets size of Contact
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of Contact
        /*taskName = new TextField(); // create task name text field
        taskName.setPrefSize(380, 20); // set size of text field
        taskName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
        taskName.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        this.getChildren().add(taskName); // add textlabel to task*/

        recipeButton = new Button(recipeTitle); // creates a button for marking the task as done
        recipeButton.setPrefSize(100, 20);
        recipeButton.setPrefHeight(Double.MAX_VALUE);
        recipeButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button

        this.getChildren().add(recipeButton);
        recipeButton.setOnAction(e -> {
            //String query = view.getQuery();
            String query = recipeTitle;
            String dummyRecipeTitle = "Recipe-title";//replace with query
            //String dummyTitle = "Recipe Title";
            //String dummyRecipe = "Recipe of title";
            String response = model.performRequest("GET", null, null, dummyRecipeTitle);
            //System.out.println(response);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Response");
            alert.setHeaderText(null);
            alert.setContentText(response);
            alert.showAndWait();

            //we would call a new screen here the getter would pass on response and title to the detailed view screen
        });
    }

    public Button getRecipeButton() {
        return recipeButton;
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

    //setter for recipe
    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
