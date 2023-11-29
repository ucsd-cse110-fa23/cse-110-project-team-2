package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class GPTHandler implements HttpHandler{

    BusinessLogic bl;

    public GPTHandler(BusinessLogic bl){
        this.bl = bl;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();
        System.out.println(method);

        try {
            if (method.equals("POST")) {
                response = handlePost(httpExchange);
            }
            else {
                throw new Exception("Not Valid Request Method");
            }
        } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }
        // Sending back response to the client
        //System.out.println(response);
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        // File f = new File("test.txt");
        OutputStream outStream = httpExchange.getResponseBody();
        // OutputStream outStream = new FileOutputStream(f);
        OutputStreamWriter out = new OutputStreamWriter(outStream, StandardCharsets.UTF_8);
        out.write(response);
        out.close();
    }
    private String handlePost(HttpExchange httpExchange) throws IOException, InterruptedException, URISyntaxException{
        InputStream inStream = httpExchange.getRequestBody();
        String requestBody = new String(inStream.readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("request body: " + requestBody);
        
        //Expects a json to be used as request body, with ingredients and type as keys
        /*JSONObject requestJson = new JSONObject(requestBody);
        String ingredients = requestJson.getString("ingredients");
        String mealtype = requestJson.getString("type");
        JSONObject responseJson = new JSONObject();*/
        String test = """
        Ingredients:
        
        -2 large eggs
        
        -3 ounces of cooked ham, cubed
        
        -2 tablespoons of butter
        
        -½ cup of shredded cheese (cheddar, Parmesan, or your preferred type)
        -½ teaspoon of Italian seasoning
        -Salt and pepper to taste
        
        
        """;
        /*responseJson.put("recipe",test);
        //responseJson.put("recipe",bl.generate(ingredients, mealtype));
        responseJson.put("title",bl.generateTitle(ingredients, mealtype));
        System.out.println(responseJson.getString("recipe"));
        String response = responseJson.toString();*/

        // new code starts here
        String[] parseData = requestBody.split("@");
        String ingredients = parseData[0];
        String mealtype = parseData[1];

        String recipe = test;
        String recipeTitle = "title";

        String response = recipeTitle + "@" + recipe;
        System.out.println("response:" + response);
        
        return response;
    }
}
