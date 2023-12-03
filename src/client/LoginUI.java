package client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

class LoginUI extends GridPane {
    private Label username;
    private Label password;
    private TextField usernameInput;
    private TextField passwordInput;
    private Button createButton;
    private Button loginButton;
    private CheckBox rememberOption;

    LoginUI() {
        username = new Label("Username:");
        usernameInput = new TextField(); 
        usernameInput.setMaxSize(150, 5);
        usernameInput.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        usernameInput.setPadding(new Insets(5, 0, 5, 0)); 
        this.addRow(0, username, usernameInput);

        password = new Label("Password:");
        passwordInput = new TextField(); 
        passwordInput.setMaxSize(150, 5);
        passwordInput.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        passwordInput.setPadding(new Insets(5, 0, 5, 0)); 
        this.addRow(1, password, passwordInput);

        rememberOption = new CheckBox("Remember Me?");
        this.addRow(10, rememberOption);

        createButton = new Button("Create");
        loginButton = new Button("Login");
        this.addRow(12, loginButton, createButton);

        this.setHgap(5);
        this.setVgap(5);
        this.setAlignment(Pos.CENTER);
        addListeners();
    }

    public String getUsernameInput() {
        return usernameInput.getText();
    }
    public String getPasswordInput() {
        return passwordInput.getText();
    }

    public Boolean checkAutomaticLogin() {
        return rememberOption.isSelected();
    }

    public void addListeners(){
        loginButton.setOnAction(e -> {
            if (checkAutomaticLogin()) {
                System.out.println("Automatic Login has been enabled");  
            }
            String username = getUsernameInput();
            String password = getPasswordInput();
            System.out.println(username);
            System.out.println(password);
            // Check login validation here
            moveToNextScreen();
        });

        createButton.setOnAction(e -> {
            if (checkAutomaticLogin()) {
                System.out.println("Automatic login was selected but won't be enabled by clicking 'create'");
            }
            String username = getUsernameInput();
            String password = getPasswordInput();
            System.out.println(username);
            System.out.println(password);
            // Post username key if it doesn't exist before moving to next screen
            moveToNextScreen();
        });
    }
    public void moveToNextScreen() {
        Scene scene = getScene();
        Window screen = scene.getWindow();
        if (screen instanceof Stage) {
            Stage current = (Stage) screen;
            HomeScreen screenTwo = new HomeScreen();
            current.setScene(new Scene(screenTwo, 500, 500));
            current.setResizable(false);
            current.show();
        }
    }
}
