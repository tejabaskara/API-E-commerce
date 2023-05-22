package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class orders {
    public static class handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())){
                OutputStream outputStream = exchange.getResponseBody();
                String reponseToBeSentBack = "Data data order yang ada di dalam database";
                exchange.sendResponseHeaders(200, reponseToBeSentBack.length());

                outputStream.write(reponseToBeSentBack.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }
    }
}
