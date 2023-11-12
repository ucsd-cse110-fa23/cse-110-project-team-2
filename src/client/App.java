package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        HomeScreen root = new HomeScreen(new RecipeList());
        primaryStage.setTitle("PantryPal");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        
        /*View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);

        Scene scene = new Scene(view.getGrid(), 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MyServerUI");
        primaryStage.show();*/

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