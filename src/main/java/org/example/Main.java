package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException{
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8065), 0);
        httpServer.createContext("/movies", new MovieHandler());
        httpServer.setExecutor(Executors.newSingleThreadExecutor());
        httpServer.start();
    }

    public static class MovieHandler implements HttpHandler{

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())){
                OutputStream outputStream = exchange.getResponseBody();
                String reponseToBeSentBack = "hello world";
                exchange.sendResponseHeaders(200, reponseToBeSentBack.length());

                outputStream.write(reponseToBeSentBack.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }
    }
}