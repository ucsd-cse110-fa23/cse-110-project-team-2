package server;

import com.sun.net.httpserver.*;

import javafx.scene.shape.Path;

import java.io.*;
import java.net.*;
import java.util.*;

public class RequestRecording implements HttpHandler {
    Model model;

    public RequestRecording(Model model) {// doesn't need a model
        this.model = model;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("POST")) {
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

    private String handlePost(HttpExchange httpExchange) throws IOException {
        InputStream inStream = httpExchange.getRequestBody();
        //Get filename from header - taken from ChatGPT 3.5
        // prompt used: "write me code for receiving a file through an http request using java"
        String fileName = httpExchange.getRequestHeaders().getFirst("Content-Disposition")
                        .replaceFirst(".*filename=\"([^\"]+)\".*", "$1");

        // Save the uploaded file to the server
        OutputStream outputStream = new FileOutputStream("recording2.wav");
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
        inStream.close();
        //end of ChatGPT code

        //Create response through Whisper
        File f = new File("recording2.wav");
        model.setRecording(f);
        String response = "recording uploaded to database";
        return response;

        /*String CRLF = "\r\n";
        int fileSize = 0;
        // expected file format to receive
        String FILE_TO_RECEIVED = "recording.wav";

        File f = new File(FILE_TO_RECEIVED);
        if (!f.exists()) {
            f.createNewFile();
        }
        InputStream input = httpExchange.getRequestBody();
        String nextLine = "";
        //reads header to see the information of the audio file
        do {
            nextLine = readLine(input, CRLF);
            if (nextLine.startsWith("Content-Length:")) {
                fileSize = Integer.parseInt(
                        nextLine.replaceAll(" ", "").substring(
                                "Content-Length:".length()));
            }
            System.out.println(nextLine);
        } while (!nextLine.equals(""));
        //once we read header and get filesize then do the following
        byte[] midFileByteArray = new byte[fileSize];
        int readOffset = 0;
        //stores the bytes we wrote in performRequestRecording into conn, our httpExchange, we used the passed in httpExchange
        //to read the context it has been written by the request, InputStream input = httpExchange.getRequestBody();
        while (readOffset < fileSize) {
            int bytesRead = input.read(midFileByteArray, readOffset, fileSize);
            readOffset += bytesRead;
        }
        // buffered output stream will write the data from the MidFIleByteArray into the file we created
        // by opening the output stream of the file
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(FILE_TO_RECEIVED))) {
            bos.write(midFileByteArray, 0, fileSize);
            /*
             * write(byte[] b, int off, int len)
             * Writes len bytes from the specified byte array starting at offset off to this buffered output stream.
             */
            /*bos.flush();
        }

        httpExchange.sendResponseHeaders(200, 0);

        //now that file is populated with our audio, then set the models file to this audio file
        model.setRecording(f);// might have to set the model to an audio file specifics, use recorder.java for reference

        String response = "did it work?";
        return response;*/
    }

    /*private static String readLine(InputStream is, String lineSeparator)
            throws IOException {
                
        int off = 0, i = 0;
        byte[] separator = lineSeparator.getBytes("UTF-8");
        byte[] lineBytes = new byte[1024];

        while (is.available() > 0) {
            int nextByte = is.read();
            if (nextByte < -1) {
                throw new IOException(
                        "Reached end of stream while reading the current line!");
            }
            lineBytes[i] = (byte) nextByte;
            if (lineBytes[i++] == separator[off++]) {
                if (off == separator.length) {
                    return new String(
                            lineBytes, 0, i - separator.length, "UTF-8");
                }
            } else {
                off = 0;
            }
            if (i == lineBytes.length) {
                throw new IOException("Maximum line length exceeded: " + i);
            }
        }
        throw new IOException(
                "Reached end of stream while reading the current line!");
    }*/
}
