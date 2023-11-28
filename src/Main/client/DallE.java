package client;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class DallE implements DallEInterface{
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";
}
