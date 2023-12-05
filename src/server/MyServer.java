//package server;
package server;

//import java.io.IOException;
import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.*;

public class MyServer {
  // initialize server port and hostname
  private static final int SERVER_PORT = 8100;
  private static final String SERVER_HOSTNAME = "localhost";

  public static void main(String[] args) throws IOException {
    // create a thread pool to handle requests
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    // create a map to store data
    BusinessLogic bl = new BusinessLogic();
    ArrayList<String> data = new ArrayList<String>();
    //Map<String, String> data = new HashMap<>();

    // create a server
    HttpServer server = HttpServer.create(
        new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
        0);

    RecipeRequestHandler request = new RecipeRequestHandler(data);
    server.createContext("/", request);

    GPTHandler gpthandler = new GPTHandler(bl);
    server.createContext("/generate", gpthandler);

    WhisperHandler whisperhandler = new WhisperHandler(bl);
      
    server.createContext("/transcribe", whisperhandler);

    LoginHandler loginhandler = new LoginHandler(bl);
    server.createContext("/login", loginhandler);

    AccountHandler accounthandler = new AccountHandler(bl);
    server.createContext("/createAccount", accounthandler);
    server.setExecutor(threadPoolExecutor);

    server.start();

    System.out.println("Server started on port " + SERVER_PORT);
  }
}