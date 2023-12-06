package main.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        AppFrame root = new AppFrame();
        primaryStage.setTitle("PantryPal");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        String response = AppFrame.getRequest().performCheck();
        if(!response.equals("true")){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Server Down");
            alert.setContentText("Server is currently down. Please try again later");
            alert.showAndWait();
            primaryStage.close();
        }

        // System.out.println("TEST LOGIN" + request.performLogin("test_user", "1234"));
        // System.out.println("TEST ACC CREATE: " + request.performCreateAccount("test_user", "1234"));
        // String query = "getNext"; 
        // while (true) {
        //     String response = request.performRequest("GET", null, query);
        //     System.out.println(response);
        //     if (response.equals("Invalid")) {
        //         break;
        //     }
        //     String recipeTitle = response.substring(0,response.indexOf(",")); 
        //     String recipe = response.substring(response.indexOf(",") + 1);
        //     Date date = new Date(10);
        //     Recipe recipeObj = new Recipe("test", recipeTitle, recipe, "Breakfast", date);
        //     AppFrame.getAppRecipeList().getChildren().add(recipeObj); 
        // }
    }
    @Override
    public void stop() {
        
    }
    public static void main(String[] args) {
        launch(args);
    } 
}