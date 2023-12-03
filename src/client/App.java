package client;


import java.sql.Date;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private RequestSender request;

    @Override
    public void start(Stage primaryStage) {
        this.request = new RequestSender();
        AppFrame root = new AppFrame();
        primaryStage.setTitle("PantryPal");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setResizable(false);
        primaryStage.show(); 

        System.out.println(request.performLogin("test_user", "1234"));
        String query = "getNext"; 
        while (true) {
            String response = request.performRequest("GET", null, query);
            System.out.println(response);
            if (response.equals("Invalid")) {
                break;
            }
            String recipeTitle = response.substring(0,response.indexOf(",")); 
            String recipe = response.substring(response.indexOf(",") + 1);
            Date date = new Date(10);
            Recipe recipeObj = new Recipe(recipeTitle, recipe, date);
            AppFrame.getAppRecipeList().getChildren().add(recipeObj); 
        }
    }
    @Override
    public void stop() {
        String recipeTitle;
        String recipe;
        String uploadString;
        this.request = new RequestSender();
        for (int i = 0; i < AppFrame.getAppRecipeList().getChildren().size(); i++) {
            recipeTitle = ((Recipe)AppFrame.getAppRecipeList().getChildren().get(i)).getRecipeTitle();
            recipe = ((Recipe)AppFrame.getAppRecipeList().getChildren().get(i)).getRecipe();
            uploadString = recipeTitle + "," + recipe;
            System.out.println(uploadString);
            String response = request.performRequest("POST", uploadString, null);
        }
    }
    public static void main(String[] args) {
        launch(args);
    } 
}