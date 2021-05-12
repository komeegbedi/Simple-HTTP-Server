/*
    Author: Egbedi Kome
    Purpose: This program creates a simple http server that receives a request from the web client and responds with an html page
*/
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHTTPServer {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for request on port 8080");

        while (true){         // repeatedly wait for connections, and process

            // 1. Read HTTP request from the client socket
            // 2. Prepare an HTTP response
            // 3. Send HTTP response to the client
            // 4. Close the socket


            try (Socket socket = server.accept()) { // 1. Read HTTP request from the client socket
                // this blocking method waits until a client connects to the server
                // and then it returns the Socket object which can be used to read client request
                // and send response to client.

                //this just prints out the http request from visualization purpose
                //You can comment this in you will like to see the HTTP request

//                BufferedReader request = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//                String line = request.readLine();
//
//                while (!line.isEmpty()){
//                    System.out.println(line);
//                    line = request.readLine();
//                }//while


                // 2. Prepare an HTTP response
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                //I want to send an html page as a response
                BufferedReader response = new BufferedReader(new FileReader("index.html"));
                String input = response.readLine();
                String responseText = "";

                //we have to read the text line by line and save it in a string
                while (input != null){
                    responseText += input;
                    input = response.readLine();
                }

                //3. Send HTTP response to the client
                out.write("HTTP/1.0 200 OK\r\n");
                out.write("Content-Type: text/html\r\n");
                out.write("Content-Length: " + responseText.length() + "\r\n");
                out.write("\r\n");
                out.write(responseText);// this will contain the html text

                //4. Close the socket
                out.close();
                //request.close(); // remember to close stream if uncommented

            }

            //System.out.println("\n\n\n"); // useful for when printing the http request
        }
    }
}
