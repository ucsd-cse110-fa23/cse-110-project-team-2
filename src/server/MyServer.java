//package server;
package server;

//import java.io.IOException;
import com.sun.net.httpserver.*;

import client.Model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class MyServer {
  // initialize server port and hostname
  private static final int SERVER_PORT = 8100;
  private static final String SERVER_HOSTNAME = "localhost";

  public static void main(String[] args) throws IOException {
    // create a thread pool to handle requests
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    // create a map to store data
    //ArrayList<String> data = new ArrayList<String>();
    Model model = new Model();

    // create a server
    HttpServer server = HttpServer.create(
        new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
        0);

    // TODO: create the context
    //HttpContext context = createContext("/", new RequestHandler(data));
    RequestHandler request = new RequestHandler(data);
    server.createContext("/", request);

    RequestMeal requestMeal = new RequestMeal(model);
    server.createContext("/meal", requestMeal);

    // will need more of these and update them according to the new Controller and Model
    RequestRecording requestRecording = new RequestRecording(model);
    server.createContext("/recording", requestRecording);

    RequestTranscription requestTranscription = new RequestTranscription(model);
    server.createContext("/transcription", requestTranscription);

    RequestRecipe requestRecipe = new RequestRecipe(data, model);
    server.createContext("/recipe", requestRecipe);




    // TODO: set the executor
    server.setExecutor(threadPoolExecutor);

    // TODO: start the server
    server.start();

    System.out.println("Server started on port " + SERVER_PORT);

  }
}