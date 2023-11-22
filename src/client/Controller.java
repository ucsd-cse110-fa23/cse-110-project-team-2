package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Controller {
    public String performRequest(String method, String language, String query) {//this thing is writing into the server to the corresponding handler
        // Implement your HTTP request logic here and return the response
        try {
            String urlString = "http://localhost:8100/";
            if (query != null) {
                urlString += "?=" + query;//make sure you replace all spaces with underscores when you add to the urlstring
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //this is where the magic happens this is where the handlers are called
            // this is setting the current method to be called 
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("POST") || method.equals("PUT")) {//we can make the method have different types like POSTRECIPE, POSTRECORDING
                //this is then writing into a file where the requesthandler reads from
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(language);
                out.flush();
                out.close();
            }

            InputStream in = conn.getInputStream();
            String response = new String(in.readAllBytes(), StandardCharsets.UTF_8);//reads everything in the file
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
    //automate this process to reduce lines of code.
    public String performRequestMeal(String method, String mealType) {
        // Implement your HTTP request logic here and return the response
        try {
            String urlString = "http://localhost:8100/meal";
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("POST")) {// remove this later
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(mealType);
                out.flush();
                out.close();
            }

            InputStream in = conn.getInputStream();
            String response = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
    // https://stackoverflow.com/questions/37869483/transfer-audio-file-from-client-to-http-server-via-urlconnection
    // citation: transfering a File recording from client to server side.
    public String performRequestRecording(String method, File recording) {
        // Implement your HTTP request logic here and return the response
        try {
            String urlString = "http://localhost:8100/recording";
            File uploadFile = recording;
            URL url = new URI(urlString).toURL();

            String boundary = Long.toHexString(System.currentTimeMillis()); 
            String CRLF = "\r\n";// move cursor to the beginning of the next line
            String charset = "UTF-8";
            //this conn is where we write it information and the url.openConnection specifies where to go in the server
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            if (method.equals("POST")) {
                OutputStream out = conn.getOutputStream();
                //compared to OutputStreamWriter, in PrintWriter adds some specific write methods for various types like String, float, etc
                // since PrintWriter can handle files as well, it must be able to read and write into the server
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, charset), true);//autoflush set true
                
                //here we are sending information to the server of the specifis of the file
                //since it needs to know its size and details in order to read it
                writer.append("--" + boundary).append(CRLF);
                writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + uploadFile.getName() + "\"").append(CRLF);
                writer.append("Content-Length: " + uploadFile.length()).append(CRLF);
                writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(uploadFile.getName())).append(CRLF);
                writer.append("Content-Transfer-Encoding: binary").append(CRLF);
                writer.append(CRLF).flush();
                Files.copy(uploadFile.toPath(), out);//copies contents of uploadFile into outputstream(the thing the server side will read)
                out.flush();
                out.close();//will we need to remove this?
            }
            String response = "is this running?";
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
    public String performRequestTranscription(String method, String recording) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/transcription";
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("POST")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(recording);
                out.flush();
                out.close();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
    public String performRequestRecipe(String method, String language, String year, String query) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/recipe";
            if (query != null) {
                urlString += query;
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("POST") || method.equals("PUT")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(language + "," + year);
                out.flush();
                out.close();
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }   
}


//response codes code:
//int responseCode = ((HttpURLConnection) connection).getResponseCode();
//System.out.println("Response code: [" + responseCode + "]");