package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    //private AppFrame root = new AppFrame();
    private Model model;

    @Override
    public void start(Stage primaryStage) {
        AppFrame root = new AppFrame();
        primaryStage.setTitle("PantryPal");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setResizable(false);
        primaryStage.show();

        /*String query = "getNext";
        while (true) {
            String response = model.performRequest("GET", null, query);
            if (response.equals("No Data")) {
                break;
            }
        }*/
        //String response = model.performRequest("GET", null, query);//response will be an element of arraylist

        
        /*
         * String language = postData.substring(
                0,
                postData.indexOf(",")), year = postData.substring(postData.indexOf(",") + 1);
         */
        /*String query = view.getQuery();
        String response = model.performRequest("GET", null, null, query);
        view.showAlert("Response", response); */
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