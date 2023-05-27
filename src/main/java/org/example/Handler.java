package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class Handler {

    public static class handler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String[] URL = parseString(String.valueOf(exchange.getRequestURI()));
            String path = URL[1];
            String pesan = "";


            if ("GET".equals(exchange.getRequestMethod())){
                OutputStream outputStream = exchange.getResponseBody();

                if (path.equals("users")){
                    ConnectSQL isiTabel = new ConnectSQL();
                    pesan = isiTabel.selectAllUser();
                } else if (path.equals("orders")) {
                    pesan = "orders";
                } else if (path.equals("reviews")) {
                    pesan = "reviews";
                }else if (path.equals("detailOrders")) {
                    pesan = "detailOrders";
                }else if (path.equals("addresses")) {
                    pesan = "addressses";
                } else if (path.equals("products")) {
                    pesan = "products";
                } else {
                    exchange.sendResponseHeaders(400, pesan.length());
                    outputStream.write(pesan.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
                exchange.sendResponseHeaders(200, pesan.length());
                outputStream.write(pesan.getBytes());
                outputStream.flush();
                outputStream.close();
            } else if ("POST".equals(exchange.getRequestMethod())){
                OutputStream outputStream = exchange.getResponseBody();
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                int i;
                StringBuilder buf = new StringBuilder();
                while ((i = br.read()) != -1) {
                    buf.append((char) i);
                }
                br.close();
                isr.close();
                String json = buf.toString();
                if (path.equals("users")) {
                    users user = new users();
                    if (user.parseJson(json) != 1) {
                        pesan = "berhasil";
                        exchange.sendResponseHeaders(200, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    } else {
                        pesan = "data kurang";
                        exchange.sendResponseHeaders(401, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                } else if (path.equals("products")) {
                    products product = new products();
                    if (product.parseJson(json) != 1) {
                        pesan = "berhasil";
                        exchange.sendResponseHeaders(200, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    } else {
                        pesan = "data kurang";
                        exchange.sendResponseHeaders(401, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                } else if (path.equals("reviews")) {
                    reviews review = new reviews();
                    if (review.parseJson(json) != 1){
                        pesan = "berhasil";
                        exchange.sendResponseHeaders(200, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    } else {
                        pesan = "data kurang";
                        exchange.sendResponseHeaders(401, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                }
            }
        }
    }
    public static String[] parseString(String string){
        String[] hasil = string.split("/");
        return hasil;
    }

    public List<users> userList = new ArrayList<users>();
}
