
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;


public class URLHandler implements HttpHandler {
    // Pass in whatever is gonna be the place we store the account recipes
    public URLHandler() {
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("GET")) {
                response = handleGet(httpExchange);
            } else {
                throw new Exception("Not Valid Request Method");
            }
        } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }
        // Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream outStream = httpExchange.getResponseBody();
        OutputStreamWriter out = new OutputStreamWriter(outStream, StandardCharsets.UTF_8);
        // Writes it into the page
        out.write(response);
        out.close();
    }

    // Concern: "Invalid GET request" in MyServer when webpage is reloaded
    private String handleGet(HttpExchange httpExchange) throws IOException, InterruptedException, URISyntaxException{
        String response = "Invalid GET request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        String title;
        if (query != null) {
            title = query.substring(query.indexOf("=") + 1);
        }
        else {
            return response; 
        }
        // Replace unique characters in title with spaces HERE

        // You have title key so now just access Json data from the passed in object 
        // in the constructor of this class, title is done as an example below
        // How do we access image data? How image data stored in Json file?
        // @ensure image and recipe of title key are available
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder
                .append("<html>")
                .append("<body>")
                .append("<h1>")
                .append("Hello Boss ")
                .append(title)
                .append("</h1>")
                .append("</body>")
                .append("</html>");

        // encode HTML content
        response = htmlBuilder.toString();
        return response;
    }
}
