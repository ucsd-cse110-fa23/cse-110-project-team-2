package unitTests.client;

import java.io.IOException;
import java.net.URISyntaxException;
import org.json.JSONException;

import client.RegenInterface;
import javafx.util.Pair;

public class mockRegen implements RegenInterface{
    @Override
    public Pair<String, String> regen(String ingreds, String mealType)
            throws IOException, InterruptedException, URISyntaxException {
        return new Pair<String, String>("Scramble eggs with chicken on pan", "Chicken and egg scramble");
    }
}
