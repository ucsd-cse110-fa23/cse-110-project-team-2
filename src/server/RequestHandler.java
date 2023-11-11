package server;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class RequestHandler implements HttpHandler {
    private final Map<String, String> saved;
    public RequestHandler(Map<String, String> saved) {
        this.saved = saved;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();
        try {
            if (method.equals("GET")) {
              response = handleGet(httpExchange);
            } else if (method.equals("POST")) {
              response = handlePost(httpExchange);
            } else if (method.equals("PUT")) {
              response = handlePut(httpExchange);
            } else if (method.equals("DELETE")) {
               response = handleDelete(httpExchange);
            } else {
              throw new Exception("Not Valid Request Method");
            }
        } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }       
        //Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
    }

    private String handleGet(HttpExchange httpExchange) throws IOException {
        String response = "Invalid GET request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        if (query != null) {
          String recipeTitle = query.substring(query.indexOf("=") + 1);
          String recipe = saved.get(recipeTitle); // Retrieve saved from hashmap
          if (recipe != null) {
            response = recipe;
            System.out.println("Queried for " + recipeTitle + " and found " + recipe);
          } else {
            response = "No saved found for " + recipeTitle;
          }
        }
        return response;
    }
      
      private String handlePost(HttpExchange httpExchange) throws IOException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String postsaved = scanner.nextLine();
        String recipeTitle = postsaved.substring(
          0,
          postsaved.indexOf(",")
        ), recipe = postsaved.substring(postsaved.indexOf(",") + 1);
     
     
        // Store saved in hashmap
        saved.put(recipeTitle, recipe);
     
     
        String response = "Posted entry {" + recipeTitle + ", " + recipe + "}";
        System.out.println(response);
        scanner.close();
     
     
        return response;
      }    
      
    private String handlePut(HttpExchange httpExchange) throws IOException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String putsaved = scanner.nextLine();
        String recipeTitle = putsaved.substring(
            0,
          putsaved.indexOf(",")
        ), recipe = putsaved.substring(putsaved.indexOf(",") + 1);

        String response = "";
        //Editing recipe
        if(saved.containsKey(recipeTitle) == true){
            String preRecipe = saved.get(recipeTitle);
            saved.put(recipeTitle, recipe);
            response = "Updated entry {" + recipeTitle + ", " + recipe + "} (previous recipe: " + preRecipe + ")";
            scanner.close();
        }
        //Saving a recipe
        else{
            saved.put(recipeTitle, recipe);
            response = "Posted entry {" + recipeTitle + ", " + recipe + "}";
            scanner.close();
        }
        //Store saved in hashmap
        return response;
    } 
    private String handleDelete(HttpExchange httpExchange) throws IOException {
        String response = "Invalid DELETE request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        if (query != null) {
          String recipeTitle = query.substring(query.indexOf("=") + 1);
          String recipe = saved.get(recipeTitle); // Retrieve saved from hashmap
          if (recipe != null) {
            response = "Deleted entry {" + recipeTitle + ", " + recipe + "}";
            System.out.println("Queried for " + recipeTitle + " and deleted it");
            saved.remove(recipeTitle);
          } else {
            response = "No saved found for " + recipeTitle;
          }
        }
        return response;
    }
}