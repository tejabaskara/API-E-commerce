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
            String allWeb = String.valueOf(exchange.getRequestURI());
            String web = exchange.getRequestURI().getPath();
            String[] allPath = Parse.path(web);
            String path = allPath[1];
            String pesan = "";
            System.out.println(exchange.getRequestMethod());

            if ("GET".equals(exchange.getRequestMethod())){
                OutputStream outputStream = exchange.getResponseBody();
                ConnectSQL isiTabel = new ConnectSQL();
                if (Parse.checkFilter(allWeb) == 1){
                    String allQuery = exchange.getRequestURI().getQuery();
                    String[] query = Parse.filter(allQuery);
//                    System.out.println(allQuery);
//                    for (int i=0; i < query.length; i++){
//                        System.out.println(query[i]);
//                    }
                    if (query.length == 3){
                        if (path.equals("users")){
                            pesan = isiTabel.queryAllCond(path, "id", query[0], query[1], query[2]);
                        } else if (path.equals("orders")) {
                            pesan = isiTabel.queryAllCond(path, "id", query[0], query[1], query[2]);
                        } else if (path.equals("reviews")) {
                            pesan = isiTabel.queryAllCond(path, "order_id", query[0], query[1], query[2]);
                        }else if (path.equals("order_details")) {
                            pesan = isiTabel.queryAllCond(path, "order_id", query[0], query[1], query[2]);
                        }else if (path.equals("addresses")) {
                            pesan = isiTabel.queryAllCond(path, "users", query[0], query[1], query[2]);
                        } else if (path.equals("products")) {
                            pesan = isiTabel.queryAllCond(path, "id", query[0], query[1], query[2]);
                        } else {
                            pesan = "Table Not Found";
                            exchange.sendResponseHeaders(404, pesan.length());
                            outputStream.write(pesan.getBytes());
                            outputStream.flush();
                            outputStream.close();
                        }
                    } else if (query.length == 1) {
                        if (path.equals("users")){
                            pesan = isiTabel.queryOneCond(path, "id", query[0]);
                        } else if (path.equals("orders")) {
                            pesan = isiTabel.queryOneCond(path, "id", query[0]);
                        } else if (path.equals("reviews")) {
                            pesan = isiTabel.queryOneCond(path, "order_id", query[0]);
                        }else if (path.equals("order_details")) {
                            pesan = isiTabel.queryOneCond(path, "order_id", query[0]);
                        }else if (path.equals("addresses")) {
                            pesan = isiTabel.queryOneCond(path, "users", query[0]);
                        } else if (path.equals("products")) {
                            pesan = isiTabel.queryOneCond(path, "id", query[0]);
                        } else {
                            pesan = "Table Not Found";
                            exchange.sendResponseHeaders(404, pesan.length());
                            outputStream.write(pesan.getBytes());
                            outputStream.flush();
                            outputStream.close();
                        }
                    } else {
                        pesan = "Path salah";
                        exchange.sendResponseHeaders(404, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }

                    exchange.sendResponseHeaders(200, pesan.length());
                    outputStream.write(pesan.getBytes());
                    outputStream.flush();
                    outputStream.close();

                }else {
                    if (path.equals("users")){
                        if (allPath.length == 2){
                            pesan = isiTabel.selectAll(path);
                        } else if (allPath.length == 3) {
                            pesan = isiTabel.selectId(path, allPath[2]);
                        } else if (allPath.length == 4) {
                            pesan = isiTabel.selectTableUser(allPath[2], allPath[3]);
                        }
                    } else if (path.equals("orders")) {
//                    System.out.println(allPath.length);
                        if (allPath.length == 2){
                            pesan = isiTabel.selectAll(path);
                        } else if (allPath.length == 3) {
                            pesan = isiTabel.selectId(path, allPath[2]);
                        } else {
                            pesan = "Wrong Path";
                            exchange.sendResponseHeaders(404, pesan.length());
                            outputStream.write(pesan.getBytes());
                            outputStream.flush();
                            outputStream.close();
                        }
                    } else if (path.equals("reviews")) {
//                    System.out.println(allPath.length);
                        if (allPath.length == 2){
                            pesan = isiTabel.selectAll(path);
                        } else if (allPath.length == 3) {
                            pesan = isiTabel.selectOrdersId(path, allPath[2]);
                        } else {
                            pesan = "Wrong Path";
                            exchange.sendResponseHeaders(404, pesan.length());
                            outputStream.write(pesan.getBytes());
                            outputStream.flush();
                            outputStream.close();
                        }
                    }else if (path.equals("order_details")) {
//                    System.out.println(allPath.length);
                        if (allPath.length == 2){
                            pesan = isiTabel.selectAll(path);
                        } else if (allPath.length == 3) {
                            pesan = isiTabel.selectOrdersId(path, allPath[2]);
                        } else {
                            pesan = "Wrong Path";
                            exchange.sendResponseHeaders(404, pesan.length());
                            outputStream.write(pesan.getBytes());
                            outputStream.flush();
                            outputStream.close();
                        }
                    }else if (path.equals("addresses")) {
//                    System.out.println(allPath.length);
                        if (allPath.length == 2){
                            pesan = isiTabel.selectAll(path);
                        } else if (allPath.length == 3) {
                            pesan = isiTabel.selectIdUsers(path, allPath[2]);
                        } else{
                            pesan = "Wrong Path";
                            exchange.sendResponseHeaders(404, pesan.length());
                            outputStream.write(pesan.getBytes());
                            outputStream.flush();
                            outputStream.close();
                        }
                    } else if (path.equals("products")) {
//                    System.out.println(allPath.length);
                        if (allPath.length == 2){
                            pesan = isiTabel.selectAll(path);
                        } else if (allPath.length == 3) {
                            pesan = isiTabel.selectId(path, allPath[2]);
                        } else{
                            pesan = "Wrong Path";
                            exchange.sendResponseHeaders(404, pesan.length());
                            outputStream.write(pesan.getBytes());
                            outputStream.flush();
                            outputStream.close();
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
                }

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
                        pesan = "Data berhasil dimasukkan";
                        exchange.sendResponseHeaders(200, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    } else {
                        pesan = "Silahkan mengecek data kembali";
                        exchange.sendResponseHeaders(401, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                } else if (path.equals("reviews")) {
                    reviews review = new reviews();
                    if (review.parseJson(json) != 1){
                        review.insert();
                        pesan = "Data berhasil dimasukkan";
                        exchange.sendResponseHeaders(200, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    } else {
                        pesan = "Silahkan mengecek data kembali";
                        exchange.sendResponseHeaders(401, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                } else if (path.equals("addresses")) {
                    addresses address = new addresses();
                    if (address.parseJson(json) != 1){
                        address.insert();
                        pesan = "Data berhasil dimasukkan";
                        exchange.sendResponseHeaders(200, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    } else {
                        pesan = "Silahkan mengecek data kembali";
                        exchange.sendResponseHeaders(401, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                } else if (path.equals("orders")) {
                    orders order = new orders();
                    if (order.parseJson(json) != 1){
                        order.insert();
                        pesan = "Data berhasil dimasukkan";
                        exchange.sendResponseHeaders(200, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    } else {
                        pesan = "Silahkan mengecek data kembali";
                        exchange.sendResponseHeaders(401, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                } else if (path.equals("order_details")) {
                    detailOrders detail = new detailOrders();
                    if (detail.parseJson(json) != 1){
                        detail.insert();
                        pesan = "Data berhasil dimasukkan";
                        exchange.sendResponseHeaders(200, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    } else {
                        pesan = "Silahkan mengecek data kembali";
                        exchange.sendResponseHeaders(401, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                } else {
                    pesan = "Wrong Path";
                    exchange.sendResponseHeaders(404, pesan.length());
                    outputStream.write(pesan.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
            } else if ("PUT".equals(exchange.getRequestMethod())) {
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
                pesan = json;
                if ()
                exchange.sendResponseHeaders(200, pesan.length());
                outputStream.write(pesan.getBytes());
                outputStream.flush();
                outputStream.close();
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                OutputStream outputStream = exchange.getResponseBody();
                ConnectSQL hapusTabel = new ConnectSQL();
//                System.out.println(allPath.length);

                if (allPath.length == 3){
                    if (path.equals("users")) {
                        hapusTabel.deleteKolom(path, "id", allPath[2]);
                    } else if (path.equals("products")) {
                        hapusTabel.deleteKolom(path, "id", allPath[2]);
                    } else if (path.equals("reviews")) {
                        hapusTabel.deleteKolom(path, "order_id", allPath[2]);
                    } else if (path.equals("addresses")) {
                        hapusTabel.deleteKolom(path, "users", allPath[2]);
                    } else if (path.equals("orders")) {
                        hapusTabel.deleteKolom(path, "id", allPath[2]);
                    } else if (path.equals("order_details")) {
                        hapusTabel.deleteKolom(path, "order_id", allPath[2]);
                    } else {
                        pesan = "Path not found";
                        exchange.sendResponseHeaders(404, pesan.length());
                        outputStream.write(pesan.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                    pesan = "Delete berhasil";
                    exchange.sendResponseHeaders(200, pesan.length());
                    outputStream.write(pesan.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }else {
                    pesan = "Path not found";
                    exchange.sendResponseHeaders(404, pesan.length());
                    outputStream.write(pesan.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
            }
        }
    }
}
