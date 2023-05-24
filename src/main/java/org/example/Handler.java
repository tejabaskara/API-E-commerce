package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;

public class Handler {

    public static class handler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String pesan="";
            if ("GET".equals(exchange.getRequestMethod())){
                OutputStream outputStream = exchange.getResponseBody();
                String path = String.valueOf(exchange.getRequestURI());
                if (path.equals("/users")){
                    pesan = "users";
                } else if (path.equals("/orders")) {
                    pesan = "orders";
                } else if (path.equals("/reviews")) {
                    pesan = "reviews";
                }else if (path.equals("/detailOrders")) {
                    pesan = "detailOrders";
                }else if (path.equals("/addresses")) {
                    pesan = "addressses";
                }
                exchange.sendResponseHeaders(200, pesan.length());


                outputStream.write(pesan.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }
    }
}
