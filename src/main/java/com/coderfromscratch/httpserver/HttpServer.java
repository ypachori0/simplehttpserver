package com.coderfromscratch.httpserver;

import com.coderfromscratch.httpserver.config.Configuration;
import com.coderfromscratch.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) {

        System.out.println("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using port: " + conf.getPort());
        System.out.println("Using Webroot: " + conf.getWebroot());

        /*
        * ServerSocket is a socket that listens to a specific port
        * accept prompts a socket that's listening to a port to accept any
        * connections that arise
        * code executes until accept and then waits to get a connection
         */
        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStrem = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was served using my Simple Java HTTP Server</h1></body></html>";

            final String CRLF = "\r\n"; //13, 10

            String response =
                    "HTTP/1.1 200 OK" + CRLF + //status line : HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
                    "Content-Length: " + html.getBytes().length + CRLF +//HEADER
                            CRLF +
                            html +
                            CRLF + CRLF ;

            outputStream.write(response.getBytes());
            inputStrem.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
