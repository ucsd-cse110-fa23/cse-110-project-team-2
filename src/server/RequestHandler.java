//package server;
package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RequestHandler implements HttpHandler {
    //private final Map<String, String> data;
    private final ArrayList<String> data;
    private int count = 0;

    public RequestHandler(ArrayList<String> data) {
        this.data = data;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("GET")) {
                response = handleGet(httpExchange);
            } else if (method.equals("POST")) {
                response = handlePost(httpExchange);
            } else {
                throw new Exception("Not Valid Request Method");
            }
        } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }

        // Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
    }

    private String handleGet(HttpExchange httpExchange) throws IOException {
        String response = "Invalid GET request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        System.out.println(query);//we would use the query to access the , but then title can't have spaces since query hates it
        if (query != null) {//so maybe replace all spaces with _ (underscores) before its passed on in the performRequest
            String value = query.substring(query.indexOf("=") + 1);//this would give us the text after = which is the key in our situation
            String postData;
            if (count < data.size()) {//then about here we would use the key to access the data of the hasmap, and then return it's value
                postData = data.get(count);//of the key, in this case it would be the recipe, so return response will return the recipe
                response = postData;
                System.out.println("Queried for " + query + " and found " + postData);
                count += 1;
            }
            else {
                response = "Invalid";
            }
        }
        System.out.println(response);
        return response;
    }

    private String handlePost(HttpExchange httpExchange) throws IOException {
        InputStream inStream = httpExchange.getRequestBody();//our request method already wrote to it, now read it
        System.out.println(inStream.toString());
        String postData = new String(inStream.readAllBytes(), StandardCharsets.UTF_8);
        // the issue here it's only reading one line
        // Scanner scanner = new Scanner(inStream); 
        // String postData = scanner.nextLine();
        /*String language = postData.substring(
                0,
                postData.indexOf(",")), year = postData.substring(postData.indexOf(",") + 1);*/

        // Store data in hashmap
        //data.put(language, year);
        // Store data in arraylist
        data.add(postData);
        //postCount += 1;
        String recipeTitle = postData.substring(
                0,
                postData.indexOf(",")), recipe = postData.substring(postData.indexOf(",") + 1);

        String response = "Posted entry {" + recipeTitle + "," + recipe + "}";
        System.out.println("post data which is the format string,string: " + postData);
        System.out.println("data when its separated: " + response);
        //scanner.close();
        inStream.close();
        return response;
    }
}