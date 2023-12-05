
import org.json.JSONException;
import java.io.*;
import java.net.*;
import org.json.*;

public interface ChatGPTInterface {
    public String generate(String ingredients, String mealType) throws IOException, InterruptedException, URISyntaxException;
    // public String generateTitle(String ingredients, String mealType) throws IOException, InterruptedException, URISyntaxException;
}