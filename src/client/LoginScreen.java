package client;

public class LoginScreen extends Screen {
    private LoginUI login;
    
    LoginScreen() {
        super();
        setHeaderText("PantryPal");
        setCenterObject();
        setFooterButtons("", "", "");
        login = new LoginUI();
        this.setCenter(login);
    }

    @Override
    protected Screen createNextScreen() {
        return new HomeScreen(login.getUsernameInput());
    }

    @Override
    protected Screen createPreviousScreen() {
        return new HomeScreen(login.getUsernameInput());
    }
}
