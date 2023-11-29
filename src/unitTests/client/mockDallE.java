package client;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

public class mockDallE implements DallEInterface{
    @Override
    public void image(String mealType) {
        System.out.println("image generated");
    }
}