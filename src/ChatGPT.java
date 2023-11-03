import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

interface ChatGPT {
    //Sends reuqest to ChatGPT
    public void sendRequest(HttpRequest request);
    //
}
