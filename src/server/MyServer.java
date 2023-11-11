package server;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
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
       // create a map to store saved
   Map<String, String> saved = new HashMap<>();

   // create a server
   HttpServer server = HttpServer.create(
     new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
     0
   );
  // TODO: create the context
   server.createContext("/", new RequestHandler(saved));
   // TODO: set the executor
   server.setExecutor(threadPoolExecutor);
   // TODO: start the server
   server.start();

  // // TODO: create the context
  //  server.createContext("/name", new MyHandler(saved));
  //  // TODO: set the executor
  //  server.setExecutor(threadPoolExecutor);
  //  // TODO: start the server
  //  server.start();

   System.out.println("Server started on port " + SERVER_PORT);
  }
}
