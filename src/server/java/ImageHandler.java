
import java.net.URISyntaxException;
import com.sun.net.httpserver.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class ImageHandler implements HttpHandler{
    private BusinessLogic bl;

    public ImageHandler(BusinessLogic bl){
        this.bl = bl;
    } 
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();
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
        // we get image from directory
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        OutputStreamWriter out = new OutputStreamWriter(outStream, StandardCharsets.UTF_8);
        out.write(response);
        out.close();
    }

    private String handlePost(HttpExchange httpExchange) throws IOException, InterruptedException, URISyntaxException{
        /*InputStream inStream = httpExchange.getRequestBody();
        String response = new String(inStream.readAllBytes(), StandardCharsets.UTF_8);

        bl.generateImage(response);
        System.out.println("is this running?");
        return response;*/
        InputStream inStream = httpExchange.getRequestBody();
        String requestBody = new String(inStream.readAllBytes(), StandardCharsets.UTF_8);
        
        //Expects a json to be used as request body, with ingredients and type as keys
        JSONObject requestJson = new JSONObject(requestBody);
        String title = requestJson.getString("recipeTitle");
        //System.out.println(title);
        bl.generateImage(title);
        String response = title;
        return response;
    }
}
