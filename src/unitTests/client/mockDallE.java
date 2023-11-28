package client;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

public class mockDallE implements DallEInterface{
    @Override
    public String generate(String mealType) {return "regenerated";}
}