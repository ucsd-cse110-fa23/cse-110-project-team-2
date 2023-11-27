package client;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGPT{
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";
    public String generate(String ingredients, String mealType) throws IOException, InterruptedException, URISyntaxException{
        String prompt = "I would like you to create a" + mealType + " recipe for me, and you can only use the ingredients I have: " + ingredients + ". Please only generate a recipe and ingredients, and no title.";
        int maxTokens = 500;

        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);

        // Create the HTTP Client
        HttpClient client = HttpClient.newHttpClient();

        URI ur = URI.create(API_ENDPOINT);
        // Create the request object
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(ur)
        .header("Content-Type", "application/json")
        .header("Authorization", String.format("Bearer %s", API_KEY))
        .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
        .build();


        // Send the request and receive the response
        HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString()
        );
        
        // Process the response
        String responseBody = response.body();

        JSONObject responseJson = new JSONObject(responseBody);
        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedText = choices.getJSONObject(0).getString("text");
        System.out.println(generatedText);
        return generatedText;
    }

    public String generateTitle(String ingredients, String mealType) throws IOException, InterruptedException, URISyntaxException{
        String prompt = "I would like you to create a" + mealType + " recipe title for me, with these ingredients: " + ingredients + ". Please only generate a title and nothing else, and please don't put it in quotation marks.";
        int maxTokens = 50;

        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);

        // Create the HTTP Client
        HttpClient client = HttpClient.newHttpClient();

        URI ur = URI.create(API_ENDPOINT);
        // Create the request object
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(ur)
        .header("Content-Type", "application/json")
        .header("Authorization", String.format("Bearer %s", API_KEY))
        .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
        .build();


        // Send the request and receive the response
        HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString()
        );
        
        // Process the response
        String responseBody = response.body();

        JSONObject responseJson = new JSONObject(responseBody);
        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedText = choices.getJSONObject(0).getString("text");
        System.out.println(generatedText);
        return generatedText;
    }
}