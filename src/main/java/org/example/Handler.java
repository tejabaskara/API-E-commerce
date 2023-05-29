package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.Filter;
import org.json.*;

import javax.servlet.http.HttpUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Handler {

    public static class handler implements HttpHandler {

        /*
        kelas handle berguna untuk menghandle semua jenis methode yang dimasukkan ke dalam API
         */
        public void handle(HttpExchange exchange) throws IOException {
            String web = exchange.getRequestURI().getPath();
            String[] allPath = Parse.path(web);
            String path = allPath[1];
            String pesan = "";

            if ("GET".equals(exchange.getRequestMethod())){
                OutputStream outputStream = exchange.getResponseBody();
                ConnectSQL isiTabel = new ConnectSQL();
                if (path.equals("users")){
                    System.out.println(allPath.length);
                    System.out.println(allPath[1]);
                    System.out.println(allPath[2]);
                    System.out.println(allPath[3]);
                    if (allPath.length == 2){
                        pesan = isiTabel.selectAll(path);
                    } else if (allPath.length == 3) {
                        pesan = isiTabel.selectId(path, allPath[2]);
                    } else if (allPath.length == 4) {
                        pesan = isiTabel.selectTableUser(path, allPath[2], allPath[3]);
                    }
                } else if (path.equals("orders")) {
//                    System.out.println(allPath.length);
                    if (allPath.length == 2){
                        pesan = isiTabel.selectAll(path);
                    } else if (allPath.length == 3) {
                        pesan = isiTabel.selectId(path, allPath[2]);
                    } else if (allPath.length == 4) {
                        pesan = isiTabel.selectId(path, allPath[2]);
                    }
                } else if (path.equals("reviews")) {
//                    System.out.println(allPath.length);
                    if (allPath.length == 2){
                        pesan = isiTabel.selectAll(path);
                    } else if (allPath.length == 3) {
                        pesan = isiTabel.selectOrdersId(path, allPath[2]);
                    } else if (allPath.length == 4) {
                        pesan = isiTabel.selectId(path, allPath[2]);
                    }
                }else if (path.equals("detailOrders")) {
//                    System.out.println(allPath.length);
                    if (allPath.length == 2){
                        pesan = isiTabel.selectAll(path);
                    } else if (allPath.length == 3) {
                        pesan = isiTabel.selectOrdersId(path, allPath[2]);
                    } else if (allPath.length == 4) {
                        pesan = isiTabel.selectId(path, allPath[2]);
                    }
                }else if (path.equals("addresses")) {
//                    System.out.println(allPath.length);
                    if (allPath.length == 2){
                        pesan = isiTabel.selectAll(path);
                    } else if (allPath.length == 3) {
                        pesan = isiTabel.selectIdUsers(path, allPath[2]);
                    } else if (allPath.length == 4) {
                        pesan = isiTabel.selectId(path, allPath[2]);
                    }
                } else if (path.equals("products")) {
//                    System.out.println(allPath.length);
                    if (allPath.length == 2){
                        pesan = isiTabel.selectAll(path);
                    } else if (allPath.length == 3) {
                        pesan = isiTabel.selectId(path, allPath[2]);
                    } else if (allPath.length == 4) {
                        pesan = isiTabel.selectId(path, allPath[2]);
                    }
                } else {
                        pesan = "Table Not Found";
                        exchange.sendResponseHeaders(404, pesan.length());
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
                        user.insert();
                        pesan = "data berhasil dimasukkan";
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
                        product.insert();
                        pesan = "data berhasil dimasukkan";
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
                        review.insert();
                        pesan = "data berhasil dimasukkan";
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
                } else if (path.equals("addresses")) {
                    addresses address = new addresses();
                    if (address.parseJson(json) != 1){
                        address.insert();
                        pesan = "data berhasil dimasukkan";
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
                } else if (path.equals("orders")) {
                    orders order = new orders();
                    if (order.parseJson(json) != 1){
                        order.insert();
                        pesan = "data berhasil dimasukkan";
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
                } else if (path.equals("detailOrders")) {
                    detailOrders detail = new detailOrders();
                    if (detail.parseJson(json) != 1){
                        detail.insert();
                        pesan = "data berhasil dimasukkan";
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
}
