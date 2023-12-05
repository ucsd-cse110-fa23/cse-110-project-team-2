package client;


import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class App extends Application {
    private RequestSender request;

    @Override
    public void start(Stage primaryStage) {
        boolean isAlive = true;
        try {
            URL url = new URL( "http://localhost:8100" ); 
            HttpURLConnection httpConn =  (HttpURLConnection)url.openConnection();
        } catch (Exception e) {
            isAlive = false;
        }
        if(isAlive){
            this.request = new RequestSender();
            AppFrame root = new AppFrame();
            primaryStage.setTitle("PantryPal");
            primaryStage.setScene(new Scene(root, 500, 500));
            primaryStage.setResizable(false);
            primaryStage.show(); 

            String query = "getNext"; 
            while (true) {
                String response = request.performRequest("GET", null, query);
                System.out.println(response);
                if (response.equals("Invalid")) {
                    break;
                }
                String recipeTitle = response.substring(0,response.indexOf(",")); 
                String recipe = response.substring(response.indexOf(",") + 1);
                String mealType = response.substring(response.indexOf(",") + 2);
                String recipeFileName = recipeTitle.replaceAll("\\s+", "_").toLowerCase();
                Image currImage = new Image("file:"+recipeFileName+".png");
                ImageView recipeImage = new ImageView();
                recipeImage.setImage(currImage);
                Date date = new Date(10);
                Recipe recipeObj = new Recipe(recipeTitle, recipe, mealType, recipeImage, date);
                AppFrame.getAppRecipeList().getChildren().add(recipeObj); 
            }
        }
        else{
            this.request = new RequestSender();
            AppFrame root = new AppFrame();
            primaryStage.setTitle("PantryPal: Error Server Down");
            primaryStage.setScene(new Scene(root, 100, 100));
            primaryStage.setResizable(false);
            primaryStage.show(); 
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