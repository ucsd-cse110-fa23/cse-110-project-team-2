package client;

import java.sql.Date;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    //private AppFrame root = new AppFrame();
    private Model model;

    @Override
    public void start(Stage primaryStage) {
        this.model = new Model();
        AppFrame root = new AppFrame();
        primaryStage.setTitle("PantryPal");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setResizable(false);
        primaryStage.show(); 

        String query = "getNext"; 
        while (true) {
            String response = model.performRequest("GET", null, query);
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
        this.model = new Model();
        for (int i = 0; i < AppFrame.getAppRecipeList().getChildren().size(); i++) {
            recipeTitle = ((Recipe)AppFrame.getAppRecipeList().getChildren().get(i)).getRecipeTitle();
            recipe = ((Recipe)AppFrame.getAppRecipeList().getChildren().get(i)).getRecipe();
            uploadString = recipeTitle + "," + recipe;
            System.out.println(uploadString);
            //String language = view.getLanguage();
            //String year = view.getYear();
            String response = model.performRequest("POST", uploadString, null);
        }
        // executed when the application shuts down
    }
    public static void main(String[] args) {
        launch(args);
    } 
}

/*
public class App extends Application {
@Override
    public void start(Stage primaryStage) throws Exception {
        HomeScreen root = new HomeScreen();
        primaryStage.setTitle("PantryPal");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);}
} */