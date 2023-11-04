import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
@Override
    public void start(Stage primaryStage) throws Exception {
        AppFrame root = new AppFrame();
        primaryStage.setTitle("PantryPal");
        primaryStage.setScene(new Scene(root, 800, 900));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
